package com.monster.yapp.test;

import junit.framework.TestCase;

import com.monster.yapp.Tree;
import com.monster.yapp.Tree2;
import com.monster.yapp.Tree2.TreeItem2;

public class TreeTestCase extends TestCase {
	
	public void testTree() {
		Tree.TreeItem root = new Tree.TreeItem();
		System.out.println("Data: "+(byte)root.mData);
		root.mData = 'A';
		
		root.mLeft = new Tree.TreeItem();
		root.mLeft.mData = 'B';

		root.mRight = new Tree.TreeItem();
		root.mRight.mData = 'C';
		
		root.mLeft.mLeft = new Tree.TreeItem();
		root.mLeft.mLeft.mData = 'D';

		root.mLeft.mRight = new Tree.TreeItem();
		root.mLeft.mRight.mData = 'E';
		
		root.mRight.mRight = new Tree.TreeItem();
		root.mRight.mRight.mData = 'F';
		
		Tree.print(root);
	}
	
	public void testTree2() {
		Tree2.TreeItem2 root = new Tree2.TreeItem2();
		//System.out.println("Data: "+root.mData);
		root.mData = 'A';
		
		root.mLeft = new TreeItem2();
		root.mLeft.mData = 'B';
		
		root.mRight = new TreeItem2();
		root.mRight.mData = 'C';
		
		root.mLeft.mLeft = new TreeItem2();
		root.mLeft.mLeft.mData = 'D';
		
		root.mLeft.mRight = new TreeItem2();
		root.mLeft.mRight.mData = 'E';
		
		root.mRight.mRight = new TreeItem2();
		root.mRight.mRight.mData = 'F';
		
		Tree2.print(root);
	}
}
