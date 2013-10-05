package com.monster.yapp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

import android.R.array;
import android.util.Log;

public class CMFib {
	private AtomicReference<BigInteger[]> mArray;
	private AtomicInteger mCounter = new AtomicInteger(0);
	
	public CMFib() {
		BigInteger[] init = {BigInteger.ONE,BigInteger.ONE};
		mArray = new AtomicReference<BigInteger[]>(init);
	}
	
	public BigInteger getNext() {
		int cnt = mCounter.getAndIncrement();
		if (cnt<2)
			return BigInteger.ONE;
	
		
		BigInteger[] array;
		BigInteger[] newArray = new BigInteger[2];
		do {
			array = mArray.get();

			newArray[0] = array[1].add(array[0]);
			newArray[1] = array[0];
			
		} while (!mArray.compareAndSet(array, newArray));

		return newArray[0];
	}
	
}
