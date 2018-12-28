package com.maple.demo.kotlinmvp.mvp.ui.activity

import android.content.Intent
import com.maple.demo.kotlinmvp.R
import com.maple.demo.kotlinmvp.app.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun layoutResID() = R.layout.activity_splash

    override fun useStatusBar() = false
    override fun useToolbar() = false

    override fun initData() {
        super.initData()
        startActivity(Intent(this,HomeActivity::class.java))
        this.finish()
    }

}
