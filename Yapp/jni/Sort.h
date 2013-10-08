/*
 * radixSort.h
 *
 *  Created on: 07.10.2013
 *      Author: Monster
 */

#ifndef RADIXSORT_H_
#define RADIXSORT_H_

class Sort {
private:
	static void qsort(int* arr, int low, int high, int d);
public:
	static void radixSort(int* arr, int len);
	static void quickSort(int* arr, int len);
};

#endif /* RADIXSORT_H_ */
