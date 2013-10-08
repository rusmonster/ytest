package com.monster.yapp;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final int IDD_MYPROGRESS = 1;
	private static final int IDD_YESNO = 2;
	private Dialog mDlg;
	
	private TextView mText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("!!!", "onCreate");
		
		mText = (TextView)findViewById(R.id.text);
		
		registerForContextMenu(mText);
		
		System.gc();
		GCTest1.main();
		
		Garbage.main(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	DialogInterface.OnClickListener listener= new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			
		}
	};
		


	private Dialog mQuitDialog;	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menu_settings:
			showDialog(IDD_MYPROGRESS);
			return true;
		case R.id.menu_dialog:
			/*
			AlertDialog.Builder b = new AlertDialog.Builder(this);
			b.setMessage("hi").setPositiveButton("YES", listener).setNegativeButton("CLOSE", listener);
			
			AlertDialog d = b.create();
			b.show();
			*/
			if (mQuitDialog == null) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("R.string.quit_prompt")
						.setPositiveButton("R.string.yes",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										finish();
									}
								})
						.setNegativeButton("R.string.no",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								});

				mQuitDialog = builder.create();
				mQuitDialog.setCanceledOnTouchOutside(false);
			}

			mQuitDialog.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(Menu.NONE, 1, Menu.NONE, "Открыть");
		menu.add(Menu.NONE, 2, Menu.NONE, "Сохранить");
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id) {
		case IDD_MYPROGRESS:
			if (mDlg!=null) mDlg.dismiss(); mDlg=null;
			mDlg = new ProgressDialog(this);
			mDlg.setCancelable(true);
			((ProgressDialog)mDlg).setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mDlg.setTitle("hello");
			return mDlg;
			
		}
		return super.onCreateDialog(id);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mDlg!=null) mDlg.dismiss(); mDlg=null;
		Log.i("!!!", "onDestroy");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("!!!", "onPause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("!!!", "onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("!!!", "onResume");
	}

	public void tQuickSort() {
		int[] a = new int[10];
		for (int i=0; i<a.length; i++)
			a[i] = 10-i;//(int) Math.round(Math.random()*100000);
		
		//printArray("src: ", a);
		
		CMYappNDK.QuickSort(a);
		
		//printArray("dst: ", a);
		
		for (int i=1; i<a.length; i++)
			assert(a[i-1]<=a[i]);
	}
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("!!!", "onStart");
		//tQuickSort();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("!!!", "onStop");
	}
	
	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.my_btn:
			//startActivity(new Intent(this, SecActivity.class));
			startActivity(new Intent(this, ThreeActivity.class));
			break;
		case R.id.mybtn2:
			Intent i = new Intent("com.monster.yapp.my_three_act");
			i.putExtra(CMParselable.class.getCanonicalName(), new CMParselable("hi from parselable"));
			startActivity(i);
			break;
		}
	}
}
