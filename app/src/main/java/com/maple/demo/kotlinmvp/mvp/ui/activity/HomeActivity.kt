package com.maple.demo.kotlinmvp.mvp.ui.activity

import com.maple.demo.kotlinmvp.R
import com.maple.demo.kotlinmvp.app.base.BaseViewActivity
import com.maple.demo.kotlinmvp.mvp.contract.HomeContract

/**
 * author: gaogq
 * time: 2018/12/26 14:05
 * description:
 */
class HomeActivity : BaseViewActivity<HomeContract.View, HomeContract.Presenter>(), HomeContract.View {
    override fun layoutResID() = R.layout.activity_home

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showToast(msg: String) {
    }

}