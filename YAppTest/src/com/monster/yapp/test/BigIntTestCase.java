package com.monster.yapp.test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.util.Log;

import junit.framework.TestCase;

public class BigIntTestCase extends TestCase {

	private String mulBigInt(String a, String b) {
		BigInteger ba = new BigInteger(a);
		BigInteger bb = new BigInteger(b);
		
		return ba.multiply(bb).toString();
	}
	
	private String mulString(String a, String b) throws UnsupportedEncodingException, IllegalArgumentException {
		if (a==null || b==null || a.length()==0 || b.length()==0) 
			throw new IllegalArgumentException("(a==null || b==null || a.length()==0 || b.length()==0)");
		
		List<Byte> res = new ArrayList<Byte>();
		
		byte[] ba = a.getBytes("utf8");
		byte[] bb = b.getBytes("utf8");
		
		for (int i=0; i<ba.length; i++) { 
			ba[i] -= 0x30;
			if (ba[i]<0 || ba[i]>9) throw new IllegalArgumentException("ba["+i+"] = "+ba[i]);
		}
		for (int i=0; i<bb.length; i++) {
			bb[i] -= 0x30;
			if (bb[i]<0 || bb[i]>9) throw new IllegalArgumentException("bb["+i+"] = "+bb[i]);
		}
		
		byte mem;
		int k;
		byte[] tmp = new byte[bb.length+1];
		for (int i= ba.length-1; i>=0; i--) {
			Arrays.fill(tmp, (byte) 0);
			mem=0;
			for (int j=bb.length-1; j>=0; j--) {
				byte t = (byte) (ba[i]*bb[j]+mem);
				mem = (byte) (t/10);
				tmp[j+1] = (byte) (t%10);
			}
			tmp[0] = mem;
			
			mem=0;
			k=ba.length-1-i;
			for (int j=tmp.length-1; j>=0; j--) {
				if (res.size()<k+1) res.add((byte) 0);
				byte t = (byte) (tmp[j]+res.get(k)+mem);
				mem = (byte)(t/10);
				res.set(k++, (byte)(t%10));
			}
			
			while (mem>0) {
				if (res.size()<k+1) res.add((byte) 0);
				byte t = (byte) (res.get(k)+mem);
				mem = (byte)(t/10);
				res.set(k++, (byte)(t%10));
			}
		}
		
		StringBuilder sb = new StringBuilder(res.size());
		int i = res.size()-1;
		while (i>0 && res.get(i).equals(Byte.valueOf((byte) 0))) 
			i--;
		while (i>=0)
			sb.append(res.get(i--).byteValue());
		
		return sb.toString();
	}
	
	private String mulString2(String a, String b) throws UnsupportedEncodingException, IllegalArgumentException {
		if (a==null || b==null || a.length()==0 || b.length()==0) 
			throw new IllegalArgumentException("(a==null || b==null || a.length()==0 || b.length()==0)");
		
		byte[] ba = a.getBytes("utf8");
		byte[] bb = b.getBytes("utf8");
		
		for (int i=0; i<ba.length; i++) { 
			ba[i] -= 0x30;
			if (ba[i]<0 || ba[i]>9) throw new IllegalArgumentException("ba["+i+"] = "+ba[i]);
		}
		for (int i=0; i<bb.length; i++) {
			bb[i] -= 0x30;
			if (bb[i]<0 || bb[i]>9) throw new IllegalArgumentException("bb["+i+"] = "+bb[i]);
		}
		
		int[] res = new int[ba.length+bb.length];
		for (int i=0; i<ba.length; i++)
			for (int j=0; j<bb.length; j++) {
				res[i+j+1] += ba[i]*bb[j];
			}
		
		for (int i=res.length-1; i>0; i--) {
			res[i-1] += res[i]/10;
			res[i] %= 10;
		}
		
		StringBuilder sb = new StringBuilder(res.length);
		int i=0;
		while (i<res.length-1 && res[i]==0)
			i++;
		
		while (i<res.length)
			sb.append(res[i++]);

		return sb.toString();
	}
	
	public void testBigInt() throws UnsupportedEncodingException {
		final int SIZ = 200;
		
		StringBuilder sb1 = new StringBuilder(SIZ);
		StringBuilder sb2 = new StringBuilder(SIZ);
		
		int siz = (int) Math.round(Math.random()*SIZ/2) + SIZ/2;
		for (int i=0; i<siz; i++) {
			sb1.append((int)Math.floor(Math.random()*10));
		}

		siz = (int) Math.round(Math.random()*SIZ/2) + SIZ/2;
		for (int i=0; i<siz; i++) {
			sb2.append((int)Math.floor(Math.random()*10));
		}
		
		String s1 = sb1.toString();
		String s2 = sb2.toString();
		
		//s1="9"; s2="9";
		Log.d("!!!", "s1: "+s1.length()+"; s2: "+s2.length());
		
		String res1 = mulBigInt(s1, s2);
		String res2 = mulString(s1, s2);
		String res3 = mulString2(s1, s2);
		
		
		assertEquals(res1, res2);
		assertEquals(res1, res3);
	}
	
	public void test100() throws UnsupportedEncodingException {
		for (int i=0; i<100; i++)
			testBigInt();
	}
}
