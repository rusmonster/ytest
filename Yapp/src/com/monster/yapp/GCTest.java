package com.monster.yapp;

import java.util.ArrayList;

import com.monster.yapp.LeaksManager;

import android.util.Log;

public class GCTest {
    static class A {
        private String myName;
        public A(String myName) {
            this.myName = myName;
        }
    }

    public static void main(String[] args) {
        A a1 = LeaksManager.getThis().monitorObject( new A("a1") );
        A a2 = LeaksManager.getThis().monitorObject( new A("a2") );
        
        ArrayList list = LeaksManager.getThis().monitorObject( new ArrayList() );
        list.add(a1);
        A[] mas = LeaksManager.getThis().monitorObject( new A[2] );
        mas[0] = a2;
        a2 = a1;
        
        Log.d("!!!", "befor mas = "+mas);
        clear(mas);
        Log.d("!!!", "after mas = "+mas);
        a1 = null;
        a2 = null;
        
        LeaksManager.getThis().checkLeaks(false); //4
        System.gc();
        LeaksManager.getThis().checkLeaks(false); //4
        
        //System.gc();
        // дальше идет какой-то код
        ///...
    }

    private static void clear(A[] mas) {
        mas = null;
    }
}
