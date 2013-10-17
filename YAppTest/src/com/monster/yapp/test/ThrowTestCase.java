package com.monster.yapp.test;

import android.util.Log;
import junit.framework.TestCase;

public class ThrowTestCase extends TestCase {
	private void stackOverFlow() {
		stackOverFlow();
	}
	
	public void testError() {
		try {
			stackOverFlow();
		} catch(Exception e) {
			Log.e("!!!", "testError Exception: "+e);
		} catch (Throwable t) {
			Log.e("!!!", "testError Throwable: "+t);
		}
		Log.d("!!!", "testError OK");
	}

	private void rtException() {
		throw new RuntimeException("rtException");
	}
	
	public void testRt() {
		try {
			rtException();
		} catch(Exception e) {
			Log.e("!!!", "testRt Exception: "+e);
		} catch (Throwable t) {
			Log.e("!!!", "testRt Throwable: "+t);
		}
	}

	private void rtExceptionThrows() throws RuntimeException{
		throw new RuntimeException("rtExceptionThrows");
	}
	
	public void testRtThrows() {
		rtExceptionThrows();
	}
	
	private void ExceptionThrows() throws Exception {
		throw new Exception("rtExceptionThrows");
	}
	
	public void testThrows() throws Exception {
		ExceptionThrows();
	}
}
