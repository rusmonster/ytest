#include <MyNative.h>
#include "javalog.h"

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
