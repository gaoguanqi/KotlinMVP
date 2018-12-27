package com.maple.demo.kotlinmvp.mvp.presenter

import com.maple.demo.kotlinmvp.app.base.mvp.BasePresenter
import com.maple.demo.kotlinmvp.mvp.contract.HomeContract
import com.maple.demo.kotlinmvp.mvp.model.HomeModel

/**
 * author: gaogq
 * time: 2018/12/27 17:24
 * description:
 */
class HomePresenter: BasePresenter<HomeContract.Model, HomeContract.View>(), HomeContract.Presenter  {
    override fun createModel(): HomeContract.Model? = HomeModel()

}