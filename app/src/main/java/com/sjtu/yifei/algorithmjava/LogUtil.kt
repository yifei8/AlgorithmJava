package com.sjtu.yifei.algorithmjava

import android.util.Log

/**
 * [description]
 * author: yifei
 * created at 18/12/17 下午9:42
 */
public object LogUtil {
    var isOpen: Boolean = true
    var TAG: String = "LogUtil"

    fun d(key: String, message: String) {
        Log.d(key, message)
    }

    fun d(message: String) {
        Log.d(TAG, message)
    }
}