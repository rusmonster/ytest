package com.monster.yapp.test;

import android.util.Log;

import com.monster.yapp.CMSort;

import junit.framework.TestCase;

public class SortTestCase extends TestCase {
	
	private void RadixSort() {
		final int N = (int) Math.round(Math.random()*100000);
		int[] Arr = new int[N];
		
		for (int i=0; i<N; i++) {
			Arr[i] = (int)Math.round(Math.random()*(i%(N/10)))+1;
		}
		
		long tim = System.currentTimeMillis();
		CMSort.radixSort(Arr);
		
		long timgc = System.currentTimeMillis();
		tim = timgc-tim;
		
		System.gc();
		
		timgc = System.currentTimeMillis()-timgc;
		
		Log.d("!!!", "RadixSort N="+N+"; (tim/N)="+(double)tim/N+"; timgc="+timgc);
		for (int i=1; i<N; i++)
			assertTrue("i="+i, Arr[i-1]<=Arr[i]);
		
	}
	private void QuickSort() {
		final int N = (int) Math.round(Math.random()*100000);
		int[] Arr = new int[N];
		
		for (int i=0; i<N; i++) {
			Arr[i] = (int)Math.round(Math.random()*(i%(N/10)))+1;
		}
		
		long tim = System.currentTimeMillis();
		CMSort.quickSort(Arr);
		tim = System.currentTimeMillis()-tim;
		Log.d("!!!", "QuickSort N="+N+"; (tim/N)="+(double)tim/N);
		
		for (int i=1; i<N; i++)
			assertTrue("i="+i, Arr[i-1]<=Arr[i]);
		
	}
	
	public void testRadixSort() {
		for (int i=0; i<100; i++) {
			RadixSort();
			Log.d("!!!", "Radix ok i="+i);
		}
	}

	public void testQuickSort() {
		for (int i=0; i<100; i++) {
			QuickSort();
			Log.d("!!!", "Quick ok i="+i);
		}
	}
}
