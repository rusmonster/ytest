/*
 * CQueue.cpp
 *
 *  Created on: 08.10.2013
 *      Author: Monster
 */

#include "CQueue.h"

CQueue::CQueue() {

}

CQueue::~CQueue() {

}

void CQueue::push(int num) {
	LOGI("push: %d", num);
	mS1.push(num);
}

int CQueue::pop() {
	int ret = -1;
	if (!mS2.empty()) {
		ret = mS2.top();
		mS2.pop();
	}
	else {

		while(!mS1.empty()) {
			mS2.push(mS1.top());
			mS1.pop();
		}

		if (!mS2.empty()) {
			ret = mS2.top();
			mS2.pop();
		}
	}
	LOGI("pop: %d", ret);
	return ret;
}

#ifdef __TEST

int CQueue::testQueue1() {

	push(1);
	push(2);
	push(3);

	if (pop()!=1) return 1;
	if (pop()!=2) return 1;
	if (pop()!=3) return 1;

	return 0;
}

int CQueue::testQueue2() {

	push(1);
	push(2);
	push(3);

	if (pop()!=1) return 1;

	push(4);
	push(5);

	if (pop()!=2) return 1;
	if (pop()!=3) return 1;
	if (pop()!=4) return 1;
	if (pop()!=5) return 1;

	return 0;
}

#endif
