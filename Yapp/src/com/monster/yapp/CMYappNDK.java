package com.monster.yapp;

public class CMYappNDK {
	
	// �������� ������ �AndroidNDK� � �������� ����������, � ������� ����������� ������. 
	// �������� ����� ������ �������� � ����� Android.mk.
	static {
		System.loadLibrary("Yapp");
	}
	
	public static native void SetString(String str);
	public static native void ChangeString();
	public static native String GetString(); 
}