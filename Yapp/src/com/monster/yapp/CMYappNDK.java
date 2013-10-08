package com.monster.yapp;

public class CMYappNDK {
	
	static {
		System.loadLibrary("Yapp");
	}
	
	public static native void SetString(String str);
	public static native void ChangeString();
	public static native String GetString(); 
	public static native void SetObserver(IMNativeCallback observer);
	public static native void RadixSort(int[] array);
	public static native void QuickSort(int[] array);
	public static native int Test();
}