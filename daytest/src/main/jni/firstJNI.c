#include <jni.h>

JNIEXPORT jstring JNICALL
Java_test_up_day_day_com_daytest_JNITestActivity_testJni(JNIEnv *env, jobject instance) {

    // TODO

    jstring returnValue = "just first test of jni";

    return (*env)->NewStringUTF(env, returnValue);
}