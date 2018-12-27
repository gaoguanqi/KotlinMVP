package com.maple.demo.kotlinmvp.app.base.mvp

/**
 * @author chenxz
 * @date 2018/11/18
 * @desc IView
 */
interface IView {
    /**
     * 显示加载
     */
    fun showLoading()

    /**
     * 隐藏加载
     */
    fun hideLoading()



    /**
     * 显示信息
     */
    fun showToast(msg: String)



}