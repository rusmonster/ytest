package com.monster.yapp.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.monster.yapp.CMYappNDK;
import com.monster.yapp.IMNativeCallback;

import junit.framework.TestCase;

public class NDKTestCase extends AndroidTestCase {
	
	private final static String TAG = "!!!";
	private IMNativeCallback mCallback = new IMNativeCallback() {
		
		@Override
		public void addLog(String text) {
			Log.e(TAG, "logNative threadId: "+Thread.currentThread().getId());
			Log.e(TAG, "logNative: "+text);
		}
	};
	
	public void testNDK() {
		Log.d(TAG, "testNDK threadId: "+Thread.currentThread().getId());
		
		CMYappNDK.SetString("hi all");
		String str = CMYappNDK.GetString();
		Log.d(TAG, "str1: "+str);
		
		CMYappNDK.ChangeString();
		str = CMYappNDK.GetString();
		Log.d(TAG, "str2: "+str);
		
		CMYappNDK.SetObserver(mCallback);
		str = CMYappNDK.GetString();
		Log.d(TAG, "str3: "+str);
	}
}
