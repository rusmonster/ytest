/*
 * radixSort.cpp
 *
 *  Created on: 07.10.2013
 *      Author: Monster
 */

#include <string.h>
#include "Sort.h"
#include "def.h"
/*
void Sort::radixSort(int* arr, int len) {
	if (!arr || len<=0) return;

	int i;
	int m = arr[0];
	for (i=1; i<len; i++)
		if (arr[i]>m)
			m=arr[i];

	int exp=1;
	int* tmp = new int[len];
	int basket[10];

	while (exp<=m) {

		memset(basket,0,sizeof(int)*10);

		for (i=0; i<len; i++)
			basket[arr[i]/exp%10]++;

		for (i=9; i>0; i--)
			basket[i-1] += basket[i];

		for (i=len-1; i>=0; i--)
			tmp[--basket[arr[i]/exp%10]] = arr[i];

		memcpy(arr, tmp, sizeof(int)*len);

		exp *= 10;
	}

	delete[] tmp;
}
*/
void Sort::radixSort(int* arr, int len) {
	if (!arr || len<=0) return;

	int i;
	int m = arr[0];
	for (i=1; i<len; i++)
		if (arr[i]>m)
			m=arr[i];

	int exp4=0;
	int* tmp = new int[len];
	int basket[16];

	while ((1<<exp4)<=m) {

		memset(basket,0,sizeof(int)*16);

		for (i=0; i<len; i++) {
			basket[(arr[i]>>exp4) & 0x0F]++;
		}

		for (i=15; i>0; i--)
			basket[i-1] += basket[i];

		for (i=len-1; i>=0; i--) {
			tmp[--basket[(arr[i]>>exp4) & 0x0F]] = arr[i];
		}

		memcpy(arr, tmp, sizeof(int)*len);

		exp4 += 4;
	}

	delete[] tmp;
}


void Sort::qsort(int* arr, int low, int high, int d) {

	LOGI("low: %d; high: %d; d: %d", low, high, d);
	//if (d>=10) return;

	int m = arr[(high+low)/2];
	int l = low;
	int r = high;

	do {
		while (arr[l]<m) l++;
		while (arr[r]>m) r--;
		if (l<=r) {
			int tmp = arr[l];
			arr[l]=arr[r];
			arr[r]=tmp;
			l++;
			r--;
		}
	} while (r>=l);

	if (low<r) qsort(arr, low, r, d+1);
	if (high>l) qsort(arr, l, high, d+1);
}

void Sort::quickSort(int* arr, int len) {
	if (!arr || len<=0) return;

	qsort(arr, 0, len-1, 0);
}

