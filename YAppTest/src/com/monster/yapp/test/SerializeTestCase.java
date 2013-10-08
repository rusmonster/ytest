package com.monster.yapp.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.os.Bundle;

import junit.framework.TestCase;

public class SerializeTestCase extends TestCase {

	private static class B {
		private final String mStr;
		public B(String str) {
			mStr = str;
		}
		public String getStr() { return mStr; }
	}
	
	private static class A implements Serializable{
		int k;
		private B mB;
		public A(int n) {
			k=n;
		}
		
		public int getK() { return k; }
		public void setB(B b) { mB = b; }
		public B getB() { return mB; }
	}
	
	public void test1() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		
		B b = new B("hello");
		A a = new A(5);
		a.setB(b);
		
		oos.writeObject(a);
		
		oos.close();
		baos.close();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		
		A a1 = (A) ois.readObject();
		
		ois.close();
		bais.close();
		
		B b1 = a1.getB();
		
		assertEquals(5, a1.getK());
		assertEquals(b.getStr(), b1.getStr());
		
	}
	
	
}
