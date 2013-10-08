package com.monster.yapp;

import java.util.concurrent.TimeUnit;

import com.monster.yapp.service.CMService;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.TimeUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ThreeActivity extends Activity {
	
	public static final int TASK1 = 1;
	public static final int TASK2 = 2;
	public static final int TASK3 = 3;
	
	public static final int EVENT_START = 1;
	public static final int EVENT_STOP = 2;

	public static final String PARAM_TASK= "PARAM_TASK";
	public static final String PARAM_TIM="PARAM_TIM";
	public static final String PARAM_PI="PARAM_PI";
	public static final String PARAM_RESULT="PARAM_RESULT";

	private TextView mText;
	private EditText mEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_three);
		mText = (TextView) findViewById(R.id.text);
		mEdit = (EditText) findViewById(R.id.txtEdit);
		
		Intent i = getIntent();
		CMParselable p = null;
		if (i!=null) {
			Log.i("!!!", "CanonicalClassName: "+CMParselable.class.getCanonicalName());
			p = i.getParcelableExtra(CMParselable.class.getCanonicalName());
		}
		if (p!=null) {
			mText.setText(p.getStr());
		}
		
		mEdit.setKeyListener(null);
	}
	
	public void onClick(View v) {
		PendingIntent pi = createPendingResult(TASK1, new Intent(), 0);
		Intent i = new Intent(this, CMService.class);
		i.putExtra(PARAM_TIM, 2000);
		i.putExtra(PARAM_PI, pi);
		
		startService(i);
		
		pi = createPendingResult(TASK2, new Intent(), 0);
		i = new Intent(this, CMService.class);
		i.putExtra(PARAM_TIM,3000);
		i.putExtra(PARAM_PI, pi);
		
		startService(i);

		pi = createPendingResult(TASK3, new Intent(), 0);
		i = new Intent(this, CMService.class);
		i.putExtra(PARAM_TIM,4000);
		i.putExtra(PARAM_PI, pi);
		
		startService(i);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		String str = new String();
		switch (resultCode) {
		case EVENT_START:
			str = "Task"+requestCode+" START\n";
			mEdit.append(str);
			break;
		case EVENT_STOP:
			String res = data.getStringExtra(PARAM_RESULT);
			str = "Task"+requestCode+" STOP res: "+res+"\n";
			mEdit.append(str);
			break;
		}
		
		Log.i("!!!", "onActivityResult"+requestCode+" str: "+str);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("!!!", "Three activity onStop()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("!!!", getClass().getName()+": onDestroy()");
	}

	private class CMTask extends AsyncTask<String, String, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				int k=0;
				for (String s : params) {
					TimeUnit.SECONDS.sleep(1);
					if (isCancelled()) return Boolean.FALSE;
					k++;
					publishProgress("["+k+"] "+s);
				}
			} catch (Exception e) {
				Log.e("!!!", "Error in doInBackground: "+e);
			}
			return Boolean.TRUE;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mEdit.append("onPreExecute\n");
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			mEdit.append("onPostExecute: "+result+"\n");
			mTask = null;
		}
		
		@Override
		protected void onCancelled(Boolean result) {
			super.onCancelled(result);
			mEdit.append("onCancelled\n");
			mTask=null;
		}
		
		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			
			StringBuilder sb = new StringBuilder();
			for (String v : values) {
				sb.append(v);
				sb.append("\n");
			}
			
			mEdit.append(sb.toString());
		}
		
	}
	
	private CMTask mTask;
	public void onClickTask(View v) {
		if (mTask == null) {
			mTask = new CMTask();
			mTask.execute("s1", "s2", "s3", "s4");
		}
		else 
			mTask.cancel(false);
	
	}
}
