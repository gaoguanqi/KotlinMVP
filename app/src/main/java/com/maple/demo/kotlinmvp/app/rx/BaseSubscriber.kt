package com.maple.demo.kotlinmvp.app.rx

import com.maple.demo.kotlinmvp.app.base.mvp.IView
import com.maple.demo.kotlinmvp.http.exception.ErrorStatus
import com.maple.demo.kotlinmvp.http.exception.ExceptionHandle
import com.maple.demo.kotlinmvp.mvp.model.entity.BaseBean
import com.maple.demo.kotlinmvp.utils.NetWorkUtil
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created by chenxz on 2018/6/11.
 */
abstract class BaseSubscriber<T : BaseBean>(view: IView? = null) : ResourceSubscriber<T>() {

    private var mView = view

    abstract fun onSuccess(t: T)

    override fun onComplete() {
        mView?.hideLoading()
    }

    override fun onStart() {
        super.onStart()
        mView?.showLoading()
        if (!NetWorkUtil.isConnected()) {
            mView?.showToast("当前网络不可用，请检查网络设置")
            onComplete()
        }
    }

    override fun onNext(t: T) {
        when {
            t.errorCode == ErrorStatus.SUCCESS -> onSuccess(t)
            t.errorCode == ErrorStatus.TOKEN_INVAILD -> {
                // Token 过期，重新登录
            }
            else -> mView?.showToast(t.errorMsg)
        }
    }

    override fun onError(t: Throwable) {
        mView?.hideLoading()
        mView?.showToast(ExceptionHandle.handleException(t))
    }

}