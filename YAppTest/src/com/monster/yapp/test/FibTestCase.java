package com.monster.yapp.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.util.Log;

import com.monster.yapp.CMFib;

import junit.framework.TestCase;

public class FibTestCase extends TestCase {

	private static final String TAG = "!!!";
	private static int N = 30000;
	private static int NUM_THREADS = 25;
	private static BigInteger[] mExpect;
	
	private CMFib mFib;
	ConcurrentLinkedQueue<BigInteger> mResult = new ConcurrentLinkedQueue<BigInteger>();
	
	private final Runnable mThread = new Runnable() {
		
		@Override
		public void run() {
			//final String tid = String.valueOf( Thread.currentThread().getId() );
			//Log.d(TAG, "tread start: "+tid);
			int max = N/NUM_THREADS;
			for (int i = 0; i<max; i++)
				mResult.add(mFib.getNext());
			//Log.d(TAG, "tread end: "+tid);
		}
	};
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		assertTrue(N % NUM_THREADS == 0);
		
		mFib = new CMFib();
		
		if (mExpect == null) {
			mExpect = new BigInteger[N];
			Log.d(TAG, "Start generate expect");
			mExpect[0]=mExpect[1]=BigInteger.ONE;
			for (int i=2; i<N; i++) {
				mExpect[i] = mExpect[i-1].add(mExpect[i-2]);
				//Log.d(TAG, "expect["+i+"] = "+mExpect[i].toString());
			}
			Log.d(TAG, "End generate expect");
		}
		System.gc();
	}


	public void testFib() throws Exception {

		Thread[] threads = new Thread[NUM_THREADS];
		
		for (int i=0; i<NUM_THREADS; i++) {
			threads[i] = new Thread(mThread);
		}
		
		Log.d(TAG, "Starting threads");
		for (int i=0; i<NUM_THREADS; i++) {
			threads[i].start();
		}
		
		Log.d(TAG, "Joining threads");
		for (int i=0; i<NUM_THREADS; i++) {
			threads[i].join();
		}

		assertEquals(N, mResult.size());
		
		Log.d(TAG, "Copy result...");
		ArrayList<BigInteger> res = new ArrayList<BigInteger>(mResult);
		
		Log.d(TAG, "Sort result...");
		Collections.sort(res);
		
		Log.d(TAG, "Check result...");
		for (int i=0; i<N; i++) {
			if (!mExpect[i].equals(res.get(i))) {
				Log.e(TAG, "not equals on i: "+i);
				int min=i-5; if (min<0) min=0;
				int max = i+5; if (max>N) max = N;
				for (int k=min; k<max; k++) {
					int e = mExpect[k].intValue();
					int r = res.get(k).intValue();
					Log.e(TAG, "["+k+"]: "+e+" "+r);	
				}
				
			}
			assertEquals(mExpect[i], res.get(i));
		}
		
	}
	
	public void testFinOneThread() {
		BigInteger[] res = new BigInteger[N];
		Log.d(TAG, "Generate result...");
		for (int i=0; i<N; i++)  {
			res[i] = mFib.getNext();
		}
		
		Log.d(TAG, "Check result...");
		for (int i=0; i<N; i++)  {
			assertEquals(mExpect[i], res[i]);
		}
	}
}
