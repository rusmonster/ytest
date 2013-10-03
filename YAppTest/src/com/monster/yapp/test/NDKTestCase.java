package com.monster.yapp.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.monster.yapp.CMYappNDK;

import junit.framework.TestCase;

public class NDKTestCase extends AndroidTestCase {
	
	private final static String TAG = "!!!";

	public void testNDK() {
		CMYappNDK.SetString("hi all");
		String str = CMYappNDK.GetString();
		Log.d(TAG, "str1: "+str);
		
		CMYappNDK.ChangeString();
		str = CMYappNDK.GetString();
		Log.d(TAG, "str2: "+str);
	}
}
