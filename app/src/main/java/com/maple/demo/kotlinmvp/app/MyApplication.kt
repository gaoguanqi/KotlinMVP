package com.maple.demo.kotlinmvp.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import com.maple.demo.kotlinmvp.utils.LogUtils
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

/**
 * author: gaogq
 * time: 2018/12/3 16:05
 * description:
 */
class MyApplication :Application(){

    private var refWatcher: RefWatcher? = null

    companion object {

        var instance: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val app = context.applicationContext as MyApplication
            return app.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        refWatcher = setupLeakCanary()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            LogUtils.logGGQ("onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            LogUtils.logGGQ("onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            LogUtils.logGGQ("onDestroy: " + activity.componentName.className)
        }
    }

}