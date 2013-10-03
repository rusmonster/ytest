/*
 * javalog.h
 *
 *  Created on: 04.10.2013
 *      Author: Monster
 */

#ifndef JAVALOG_H_
#define JAVALOG_H_

#include <jni.h>

class javalog {
private:
	JNIEnv& mEnv;
	JavaVM* mVm;
	jobject mObserver;
	jmethodID mAddLog;
public:
	javalog(JNIEnv& pEnv, jobject pInstance);
	virtual ~javalog();

	void addLog(char* str);
};

#endif /* JAVALOG_H_ */
