package com.maple.demo.kotlinmvp.app.rx

import com.maple.demo.kotlinmvp.app.rx.scheduler.IoMainScheduler


/**
 * Created by chenxz on 2018/4/21.
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> = IoMainScheduler()

}