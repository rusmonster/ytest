/*
 * mynative.h
 *
 *  Created on: 04.10.2013
 *      Author: Monster
 */

#ifndef MYNATIVE_H_
#define MYNATIVE_H_


#include <def.h>
#include <jni.h>

char  MyStr[80];

extern "C" {
    JNIEXPORT void Java_com_monster_yapp_CMYappNDK_SetString(JNIEnv * env, jobject obj, jstring str);
    JNIEXPORT void Java_com_monster_yapp_CMYappNDK_ChangeString(JNIEnv * env, jobject obj);
    JNIEXPORT jstring Java_com_monster_yapp_CMYappNDK_GetString(JNIEnv * env, jobject obj);
    JNIEXPORT void Java_com_monster_yapp_CMYappNDK_SetObserver(JNIEnv * env, jobject pThis, jobject pObserver);
    JNIEXPORT void Java_com_monster_yapp_CMYappNDK_RadixSort(JNIEnv * env, jobject pThis, jintArray array);
    JNIEXPORT void Java_com_monster_yapp_CMYappNDK_QuickSort(JNIEnv * env, jobject pThis, jintArray array);
    JNIEXPORT jint Java_com_monster_yapp_CMYappNDK_Test(JNIEnv * env, jobject pThis);
}

#endif /* MYNATIVE_H_ */
