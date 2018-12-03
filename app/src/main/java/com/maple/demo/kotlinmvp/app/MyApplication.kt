package com.maple.demo.kotlinmvp.app

import android.app.Application
import kotlin.properties.Delegates

/**
 * author: gaogq
 * time: 2018/12/3 16:05
 * description:
 */
class MyApplication :Application(){

    companion object {
        private var instance:MyApplication by Delegates.notNull<MyApplication>()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}