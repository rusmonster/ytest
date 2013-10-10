package com.monster.yapp;

import java.util.LinkedList;
import java.util.List;

public class Tree {
    private static final List<StringBuilder> mResult = new LinkedList<StringBuilder>();
    
    public static class TreeItem {
        public char mData;
        public TreeItem mLeft;
        public TreeItem mRight;
    }
 
    private static void printLevel(TreeItem item, int level) {
        if (item == null) return;
        
        if (mResult.size()<level+1) {
            mResult.add(new StringBuilder());
        }
        mResult.get(level).append(item.mData);
        mResult.get(level).append(' ');
        
        printLevel(item.mLeft, level+1);
        printLevel(item.mRight, level+1);
    }
    
    public static void print(TreeItem root) {
        mResult.clear();        
        printLevel(root,0);
        for (StringBuilder sb : mResult) {
            System.out.println(sb.toString());
        }
    }
}
    
