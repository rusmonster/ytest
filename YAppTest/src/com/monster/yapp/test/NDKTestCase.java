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
	
	private void printArray(String title, int[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<arr.length; i++) {
			sb.append(arr[i]);
			sb.append(',');
		}
		Log.i(TAG, title+":\n"+sb.toString());
	}
	
	public void testQuickSort() {
		int[] a = new int[1];
		for (int i=0; i<a.length; i++)
			a[i] = (int) Math.round(Math.random()*100000);
		
		printArray("src: ", a);
		
		CMYappNDK.QuickSort(a);
		
		printArray("dst: ", a);
		
		for (int i=1; i<a.length; i++)
			assertTrue("fail on i="+i, a[i-1]<=a[i]);
	}
	
	public void testRadixSort() {
		int[] a = new int[100000];
		for (int i=0; i<a.length; i++)
			a[i] = (int) Math.round(Math.random()*Math.pow(10,(i%6+1)));
		
		printArray("src: ", a);
		
		CMYappNDK.RadixSort(a);
		
		printArray("dst: ", a);
		
		for (int i=1; i<a.length; i++)
			assertTrue("fail on i="+i, a[i-1]>=a[i]);
	}
	public void testTest() {
		assertEquals(0, CMYappNDK.Test());
	}
}
