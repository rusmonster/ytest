#include <vector>
#include <string>
#include <algorithm>
#include <map>

#include <MyNative.h>
#include "javalog.h"
#include "Sort.h"
#include "CQueue.h"

javalog* myjava=0;

JNIEXPORT void Java_com_monster_yapp_CMYappNDK_SetString(JNIEnv * env, jobject obj, jstring str){
	jboolean isCopy;
	const char * Str;
	Str = env->GetStringUTFChars(str, &isCopy);
	strcpy(MyStr,Str);
	LOGI("string = \"%s\"",MyStr);
}

void ChangeStr(){
	strcat(MyStr," and bb.");
}

JNIEXPORT void Java_com_monster_yapp_CMYappNDK_ChangeString(JNIEnv * env, jobject obj){
	ChangeStr();
	LOGI("string after change = \"%s\"",MyStr);
}

JNIEXPORT jstring Java_com_monster_yapp_CMYappNDK_GetString(JNIEnv * env, jobject obj){
	LOGI("returned string = \"%s\"",MyStr);
	if (myjava) myjava->addLog(MyStr);
	return env->NewStringUTF(MyStr);
}

JNIEXPORT void Java_com_monster_yapp_CMYappNDK_SetObserver(JNIEnv * env, jobject pThis, jobject pObserver){
	LOGI("Java_com_monster_yapp_CMYappNDK_SetObserver 1");
	myjava = new javalog(*env, pObserver);
	LOGI("Java_com_monster_yapp_CMYappNDK_SetObserver 2");
}

JNIEXPORT void Java_com_monster_yapp_CMYappNDK_RadixSort(JNIEnv * env, jobject pThis, jintArray array) {
	jboolean isCopy;
	int len = env->GetArrayLength(array);
	int* arr = env->GetIntArrayElements(array, &isCopy);
	LOGI("isCopy: %d", isCopy?1:0);
	Sort::radixSort(arr,len);
	env->ReleaseIntArrayElements(array, arr, 0);

}

JNIEXPORT void Java_com_monster_yapp_CMYappNDK_QuickSort(JNIEnv * env, jobject pThis, jintArray array) {
	jboolean isCopy;
	int len = env->GetArrayLength(array);
	int* arr = env->GetIntArrayElements(array, &isCopy);
	LOGI("isCopy: %d", isCopy?1:0);
	Sort::quickSort(arr,len);
	env->ReleaseIntArrayElements(array, arr, 0);

}

JNIEXPORT jint Java_com_monster_yapp_CMYappNDK_Test(JNIEnv * env, jobject pThis) {
	jint ret=0;

	CQueue q;
	//ret += q.testQueue1();
	//ret += q.testQueue2();

	/*
	std::vector<std::string> v;
	LOGI("v.size: %d", v.size());
	v.push_back("xxx");
	v.push_back("yyy");
	v.push_back("aaa");
	v.push_back("џџџ");
	v.push_back("ссс");
	LOGI("v.size100: %d", v.size());

	for (int i=0; i<v.size(); i++) {
		LOGI("v[%d] = %s", i, v[i].c_str());
	}

	std::sort(v.begin(), v.end());

	std::vector<std::string>::iterator cur = v.begin();
	while (cur < v.end()) {
		LOGI("v[%d] = %s", cur-v.begin(), cur->c_str());
		cur++;
	}


	LOGI("%d", (int*)2+3);
*/
	std::map<int, std::string> m;
	m[1] = "hi";
	m[2] = "all";
	m[3] = "me";

	std::map<int, std::string>::iterator c = m.begin();
	while (c != m.end()) {
		LOGI("m[%d] = %s", c->first, c->second.c_str());
		c++;
	}

	m.erase(2);
	if (m.find(2) == m.end())
		LOGI("NO m2");
	return ret;
}
