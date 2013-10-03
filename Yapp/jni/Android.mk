LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Yapp
LOCAL_SRC_FILES := mynative.cpp

LOCAL_LDLIBS :=  -llog

include $(BUILD_SHARED_LIBRARY)
