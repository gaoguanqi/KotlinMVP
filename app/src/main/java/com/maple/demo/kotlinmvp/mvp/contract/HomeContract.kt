package com.maple.demo.kotlinmvp.mvp.contract

import com.maple.demo.kotlinmvp.app.base.mvp.IModel
import com.maple.demo.kotlinmvp.app.base.mvp.IPresenter
import com.maple.demo.kotlinmvp.app.base.mvp.IView

/**
 * author: gaogq
 * time: 2018/12/27 17:23
 * description:
 */
interface HomeContract {
    interface View : IView {
    }

    interface Presenter : IPresenter<View> {
    }

    interface Model : IModel {
    }
}