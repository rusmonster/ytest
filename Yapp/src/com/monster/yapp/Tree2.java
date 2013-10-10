package com.monster.yapp;

public class Tree2 {
    public static class TreeItem2 {
        public char mData;
        public TreeItem2 mLeft;
        public TreeItem2 mRight;
    }
 
    private static int printLevel(TreeItem2 item, int level, int cur_level) {
        if (item == null) return 0;
        
        if (cur_level<level) {
            return printLevel(item.mLeft, level, cur_level+1) + printLevel(item.mRight, level, cur_level+1);
        }
        else {
            System.out.print(item.mData+" ");
            return 1;
        }
            
    }
    
    public static void print(TreeItem2 root) {
        int level=0;
        while (printLevel(root,level++,0)>0)
            System.out.println();
    }
}