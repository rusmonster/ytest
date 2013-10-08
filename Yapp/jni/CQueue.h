/*
 * CQueue.h
 *
 *  Created on: 08.10.2013
 *      Author: Monster
 */

#ifndef CQUEUE_H_
#define CQUEUE_H_

#include <stack>
#include "def.h"

class CQueue {
private:
	std::stack<int> mS1;
	std::stack<int> mS2;
public:
	CQueue();
	virtual ~CQueue();
	void push(int num);
	int pop();

#ifdef __TEST
	int testQueue1();
	int testQueue2();
#endif

};

#endif /* CQUEUE_H_ */
