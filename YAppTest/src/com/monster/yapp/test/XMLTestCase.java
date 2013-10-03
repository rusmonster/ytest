package com.monster.yapp.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ContentHandler;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.sax.ElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.test.AndroidTestCase;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Xml;
import android.util.Xml.Encoding;

public class XmlTestCase extends AndroidTestCase {

	private final static String ENCODING = "ISO_8859_1";
	private final static String TAG = "!!!";
	private final static SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	
	private final static String TITLE = "title";
	
	/*
	public void testTestRes() { //fails with ResourceNotFound exception
		String str = getContext().getResources().getString(com.monster.yapp.test.R.string.test_str);
		System.out.println("test_str: "+str);
	}
*/
	
	private static class Message {
		public String title;
		public String desc;
		public String link;
		public Date date;
		
		@Override
		public Message clone() throws CloneNotSupportedException {
			Message ret = new Message();
			
			ret.title = new String(title);
			ret.link = new String(link);
			ret.desc = new String(desc);
			ret.date = new Date(date.getTime());
			
			return ret;
		}

		
	}
	
	private void printHex(byte[] mas) {
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<mas.length; i++) {
			sb.append(String.format("%02X ", mas[i]));
			if (i>0 && i%16==0) sb.append("\n");
		}
		
		System.out.println("printHex:\n"+sb.toString());
		
	}
	
	private void printMessages(List<Message> list) {
		int i=0;
		for (Message m : list) {
			Log.d(TAG, "msg: "+(++i));
			Log.d(TAG, "title = "+m.title);
			Log.d(TAG, "link = "+m.link);
			Log.d(TAG, "desc = "+m.desc);
			Log.d(TAG, "date = "+((m.date!=null)?m.date.toString():"null"));
		}
	}
	
	public void testAppRes() {
		String str = getContext().getResources().getString(com.monster.yapp.R.string.app_str);
		System.out.println("app_str: "+str);
	}
	
	public void testDOM() throws Exception {

		/*
		int siz = xmlIs.available();
		System.out.println("siz: "+siz);
		byte[] buf = new byte[xmlIs.available()];
		xmlIs.read(buf);
		String xml = new String(buf, ENCODING);
		printHex(buf);
		System.out.println("xml: "+xml);
		*/

		InputStream xmlIs = getContext().getResources().openRawResource(com.monster.yapp.R.raw.test);
		List<Message> messsages = new LinkedList<Message>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document dom = builder.parse(xmlIs);
		
		Element root = dom.getDocumentElement();
		assertEquals("rss", root.getNodeName());
		
		NodeList items = root.getElementsByTagName("item");
		
		for (int i=0; i<items.getLength(); i++) {
			Message msg = new Message();
			Node item = items.item(i);
			
			Node tmp = item.getFirstChild();
			while(tmp!=null) {
				String nam = tmp.getNodeName();
				
				if (nam.equals(TITLE))
					msg.title = tmp.getTextContent();
				else if (nam.equals("description")) 
					msg.desc = tmp.getFirstChild().getNodeValue();
				else if (nam.equals("link"))
					msg.link = tmp.getTextContent();
				else if (nam.equals("pubDate"))
					msg.date = FORMATTER.parse(tmp.getTextContent().trim());

				tmp = tmp.getNextSibling();
			}
			
			messsages.add(msg);
		}
		
		printMessages(messsages);
	}
	
	private static class RssHandler extends DefaultHandler {

		private final List<Message> mList;
		private StringBuilder mBuilder = new StringBuilder();
		private Message mCurrent;
		
		public RssHandler(List<Message> list) {
			mList = list;
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);
			mBuilder.append(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			super.endElement(uri, localName, qName);
			if (localName.equals("item")) {
				if (mCurrent!=null) mList.add(mCurrent); mCurrent = null;
			}
			else if (mCurrent!=null) {
				if (localName.equals("title"))
					mCurrent.title = mBuilder.toString().trim();
				else if (localName.equals("description"))
					mCurrent.desc = mBuilder.toString().trim();
				else if (localName.equals("link"))
					mCurrent.link = mBuilder.toString().trim();
				else if (localName.equals("pubDate"))
					try {
						mCurrent.date = FORMATTER.parse(mBuilder.toString().trim());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			mBuilder.setLength(0);
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			
			super.startElement(uri, localName, qName, attributes);
			if (localName.equals("item")) {
				mCurrent = new Message();
			}
		}
		
	}
	
	public void testSAX() throws Exception {
		InputStream xmlIs = getContext().getResources().openRawResource(com.monster.yapp.R.raw.test);
		List<Message> messsages = new LinkedList<Message>();
		
		Xml.parse(xmlIs, Encoding.UTF_8, new RssHandler(messsages));
		
		printMessages(messsages);
	}

	public void testSAXAndroid() throws Exception {
		InputStream xmlIs = getContext().getResources().openRawResource(com.monster.yapp.R.raw.test);
		final List<Message> messsages = new LinkedList<Message>();
		final Message msg = new Message();
		
		RootElement root = new RootElement("rss");
		android.sax.Element channel = root.getChild("channel");
		android.sax.Element item = channel.getChild("item");
		
		android.sax.Element title = item.getChild("title");
		android.sax.Element link = item.getChild("link");
		android.sax.Element desc = item.getChild("description");
		android.sax.Element date = item.getChild("pubDate");
		
		item.setElementListener(new ElementListener() {
			
			@Override
			public void end() {
				try {
					messsages.add(msg.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void start(Attributes arg0) {
				msg.title = msg.link = msg.desc = null;
				msg.date = null;
			}
		});
		
		title.setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String arg0) {
				msg.title = arg0.replace("\n", "");
			}
		});
		
		desc.setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String arg0) {
				msg.desc = arg0.replace("\n", "");
			}
		});
		
		link.setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String arg0) {
				msg.link = arg0.replace("\n", "");
			}
		});
		
		date.setEndTextElementListener(new EndTextElementListener() {
			
			@Override
			public void end(String arg0) {
				try {
					msg.date = FORMATTER.parse(arg0);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Xml.parse(xmlIs, Encoding.UTF_8, root.getContentHandler());
		printMessages(messsages);
	}
	
	public void testXmlPull() throws Exception {
		
		InputStream xmlIs = getContext().getResources().openRawResource(com.monster.yapp.R.raw.test);
		final List<Message> messsages = new LinkedList<Message>();
		final Message msg = new Message();
		XmlPullParser parser = Xml.newPullParser();
		
		parser.setInput(xmlIs, "UTF-8");
		
		String nam;
		
		int event = parser.getEventType();
		while (event != parser.END_DOCUMENT) {
			switch(event) {
			case XmlPullParser.START_TAG:
				nam = parser.getName();
				if (nam.equals("title"))
					msg.title = parser.nextText();
				else if (nam.equals("link"))
					msg.link = parser.nextText();
				else if (nam.equals("description"))
					msg.desc = parser.nextText();
				else if (nam.equals("pubDate"))
					msg.date = FORMATTER.parse(parser.nextText());
				break;
			case XmlPullParser.END_TAG:
				nam = parser.getName();
				if (nam.equals("item"))
					messsages.add(msg.clone());				
				break;
			}
			
			event = parser.next();
		}
		
		printMessages(messsages);
	}
	
	public void testJson() throws Exception {
		InputStream xmlIs = getContext().getResources().openRawResource(com.monster.yapp.R.raw.json);
		byte[] buf = new byte[xmlIs.available()];
		xmlIs.read(buf);
		String str = new String(buf, "UTF8");
		Log.d(TAG, "str: "+str);
		JSONObject obj = new JSONObject(str);
		
		String nam = obj.getString("name");
		int age = obj.getInt("age");
		
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
		String dat = obj.getString("birthday");
		
		Date date = df.parse(dat);
		
		Log.d(TAG, "name: "+nam+"\nage: "+age+"\ndate: "+date);
	}
	
	public void testBuildJson() throws Exception {
		
		JSONObject json = new JSONObject();
		
		json.put("name", "monster");
		json.put("birth", "05/07/1983");
		json.put("age", 30);
		
		String res = json.toString();
		
		Log.d(TAG, "res:\n"+res);

		JSONArray arr = new JSONArray();
		
		arr.put("testVal");
		arr.put(json);
		arr.put(json);
		
		
		Log.d(TAG, "arr:\n"+arr.toString());
		
		JSONObject bigObj = new JSONObject();
		
		bigObj.put("res", json);
		bigObj.put("arr", arr);
		
		Log.d(TAG, "bigObj\n"+bigObj.toString());
	}
}
