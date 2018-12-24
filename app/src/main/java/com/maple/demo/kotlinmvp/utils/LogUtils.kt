package com.maple.demo.kotlinmvp.utils

import android.util.Log

/**
 * author: gaogq
 * time: 2018/12/24 16:05
 * description:
 */
class LogUtils {
    companion object {
        private val isShow:Boolean = true
        private val TAG:String = "TAG"
        fun logGGQ(msg:String){if(isShow)Log.i(TAG,msg)}
    }
}