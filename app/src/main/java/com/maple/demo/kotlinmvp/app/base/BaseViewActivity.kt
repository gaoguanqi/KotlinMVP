package com.maple.demo.kotlinmvp.app.base

import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView
import com.maple.demo.kotlinmvp.R
import com.maple.demo.kotlinmvp.app.base.mvp.IPresenter
import com.maple.demo.kotlinmvp.app.base.mvp.IView
import kotlinx.android.synthetic.main.layout_base.*

/**
 * author: gaogq
 * time: 2018/12/27 17:27
 * description:
 */
abstract class BaseViewActivity <in V : IView, P : IPresenter<V>> : BaseActivity(), IView, View.OnClickListener {
    var mMultipleStatusView: MultipleStatusView? = null
    override fun onContentView() {
        setContentView(R.layout.layout_base)
        mMultipleStatusView = findViewById(R.id.multiple_status_view) as MultipleStatusView
        mMultipleStatusView?.showContent(layoutResID(), ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        //设置重试视图点击事件
        mMultipleStatusView?.setOnRetryClickListener(this)
        if(useToolbar()){
            view_stub_title.inflate()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.empty_retry_view, R.id.error_retry_view, R.id.no_network_retry_view ->  onRefreshData()
        }
    }

    open fun onRefreshData() {}

}