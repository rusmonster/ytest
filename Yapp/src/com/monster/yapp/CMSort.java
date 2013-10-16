package com.monster.yapp;

import java.util.Arrays;

import android.graphics.Path.FillType;

public class CMSort {
	public static void radixSort(int[] A) {
		if (A==null || A.length<2) return;
		
		int max = 0; 
		for (int a : A) {
			if (a>max) 
				max=a;
		}
		
		int n=0;
		int i;
		int len = A.length;
		int[] tmp = new int[len];
		int[] basket = new int[16];
		while ((1<<n) <= max) {
			Arrays.fill(basket, 0);
			
			for (i = 0; i<len; i++) {
				basket[(A[i]>>n) & 0x0F]++;
			}
			
			for (i=1; i<16; i++)
				basket[i] += basket[i-1];
			
			for (i=len-1; i>=0; i--) {
				tmp[--basket[(A[i]>>n) & 0x0F]] = A[i];
			}
			
			System.arraycopy(tmp, 0, A, 0, len);
			
			n+=4;
		}
	}
	
	private static void qsort(int[] A, int low, int high) {
		int l=low;
		int r = high;
		int m = A[(low+high)/2];
		
		do {
			while (A[l]<m) l++;
			while (A[r]>m) r--;
			
			if (l<=r) {
				int t = A[l]; A[l]=A[r]; A[r]=t;
				l++; r--;
			}
		} while (r>=l);
		
		if (low<r) qsort(A, low,r);
		if (l<high) qsort(A, l, high);
	}
	
	public static void quickSort(int[] A) {
		if (A==null || A.length<2) return;
		
		qsort(A,0,A.length-1);
	}
}
