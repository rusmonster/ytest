package com.monster.yapp;

public class CMYappNDK {
	
	// Загрузка модуля «AndroidNDK» — нативной библиотеки, в которой реализованы методы. 
	// Название этого модуля задается в файле Android.mk.
	static {
		System.loadLibrary("Yapp");
	}
	
	public static native void SetString(String str);
	public static native void ChangeString();
	public static native String GetString(); 
}