#include <MyNative.h>

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
	return env->NewStringUTF(MyStr);
}
