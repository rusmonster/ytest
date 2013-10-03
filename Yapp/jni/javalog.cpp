/*
 * javalog.cpp
 *
 *  Created on: 04.10.2013
 *      Author: Monster
 */

#include "def.h"
#include "javalog.h"

javalog::javalog(JNIEnv& pEnv, jobject pInstance):mEnv(pEnv) {
	mEnv.GetJavaVM(&mVm);
	mObserver = mEnv.NewGlobalRef(pInstance);
	jclass cl = mEnv.GetObjectClass(pInstance);
	mAddLog = mEnv.GetMethodID(cl, "addLog", "(Ljava/lang/String;)V");
}

javalog::~javalog() {
}

void javalog::addLog(char* str) {
	LOGI("befor javaCall1");

	jobject jStr = mEnv.NewStringUTF(str);
	mEnv.CallVoidMethod(mObserver, mAddLog, jStr);
	LOGI("after javaCall");
}
