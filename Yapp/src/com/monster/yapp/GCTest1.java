package com.monster.yapp;

import java.util.ArrayList;

import android.util.Log;

public class GCTest1 {
	static {
		Log.e("!!!", "static GCTest1");
		System.out.println("OK");
	}
	
    static class A {
        private String myName;
        public A(String myName) {
            this.myName = myName;
        }
    }

    public static void main() {
        A a1 = new A("a1");
        A a2 = new A("a2");
        ArrayList list = new ArrayList();
        list.add(a1);
        A[] mas = new A[2];
        mas[0] = a2;
        a2 = a1;
        clear(mas);
        a1 = null;
        a2 = null;
        
        System.gc();
        // дальше идет какой-то код
        //...
    }

    private static void clear(A[] mas) {
        mas = null;
    }
}
