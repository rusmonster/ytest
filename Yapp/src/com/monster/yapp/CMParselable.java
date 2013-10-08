package com.monster.yapp;

import android.os.Parcel;
import android.os.Parcelable;

public class CMParselable implements Parcelable{

	private final String mStr;
	
	public CMParselable(String str) {
		mStr = str;
	}
	
	private CMParselable(Parcel in) {
		mStr = in.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mStr);
	}


	public static final Parcelable.Creator<CMParselable> CREATOR = new Creator<CMParselable>() {
		
		@Override
		public CMParselable[] newArray(int size) {
			return new CMParselable[size];
		}
		
		@Override
		public CMParselable createFromParcel(Parcel source) {
			return new CMParselable(source);
		}
	};

	public String getStr() {
		return mStr;
	}
}
