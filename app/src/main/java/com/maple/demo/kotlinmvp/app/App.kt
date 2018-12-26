package com.maple.demo.kotlinmvp.app

import android.content.Context
import com.cxz.kotlin.baselibs.app.BaseApp
import kotlin.properties.Delegates

/**
 * author: gaogq
 * time: 2018/12/26 10:47
 * description:
 */
class App :BaseApp(){
    companion object {
        var instance: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}