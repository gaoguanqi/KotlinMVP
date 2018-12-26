package com.maple.demo.kotlinmvp.app.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.maple.demo.kotlinmvp.R
import com.maple.demo.kotlinmvp.app.manager.toolbar.OnToolbarListener
import com.maple.demo.kotlinmvp.app.manager.toolbar.ToolbarConfig
import com.maple.demo.kotlinmvp.utils.CommonUtil
import com.maple.demo.kotlinmvp.utils.KeyBoardUtil
import com.maple.demo.kotlinmvp.utils.StatusBarUtil
import kotlinx.android.synthetic.main.layout_title_bar.*
import org.greenrobot.eventbus.EventBus

/**
 * author: gaogq
 * time: 2018/12/26 11:25
 * description:
 */
abstract class BaseActivity : AppCompatActivity(), OnToolbarListener {
    /**
     * 布局文件id
     */
    @LayoutRes
    protected abstract fun layoutResID(): Int

    /**
     * 是否有titleBar
     */
    open fun useToolbar(): Boolean = true

    /**
     * 是否有StatusBar
     */
    open fun useStatusBar(): Boolean = true

    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = false


    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 初始化 View
     */
    open fun initView(){}

    open fun getToolbarConfig(): ToolbarConfig? {
        return null
    }

    /**
     * 设置状态栏的背景颜色
     */
    fun setStatusBarColor(@ColorInt color: Int) {
        StatusBarUtil.setColor(this, color, 0)
    }

    /**
     * 设置状态栏图标的颜色
     *
     * @param dark true: 黑色  false: 白色
     */
    fun setStatusBarIcon(dark: Boolean) {
        StatusBarUtil.setLightStatusBar(this, dark)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // 强制竖屏
        setContentView(layoutResID())
        if(useStatusBar()){
            setStatusBarColor(this.resources.getColor(R.color.color_toolbar));
        }
        if(useToolbar()){
          setToolbar()
        }
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        initView()
        initData()
    }

    private fun setToolbar() {
        val config:ToolbarConfig? = getToolbarConfig();
        if(config == null){
            return
        }

        //设置UI标题
        if(config.hasTitle){
            toolbar_title.visibility = View.VISIBLE
            toolbar_title.text = config.title
        }
        //返回文字
        if(config.hasBackText){
            tv_bar_back.visibility = View.VISIBLE
            tv_bar_back.text = config.backText
            tv_bar_back.setOnClickListener({ v -> config.listener?.onToolbarClick(R.id.tv_bar_back) })
        }
        //设置文字
        if(config.hasSettingText){
            tv_bar_setting.visibility = View.VISIBLE
            tv_bar_setting.text = config.settingText
            tv_bar_setting.setOnClickListener({v ->config.listener?.onToolbarClick(R.id.tv_bar_setting)})
        }
        //返回按钮
        if(config.hasBack){
            ibtn_bar_back.visibility = View.VISIBLE
            ibtn_bar_back.setOnClickListener({v -> config.listener?.onToolbarClick(R.id.ibtn_bar_back) })
        }
        //设置按钮
        if(config.hasSetting){
            ibtn_bar_setting.visibility = View.VISIBLE
            ibtn_bar_setting.setOnClickListener({v -> config.listener?.onToolbarClick(R.id.ibtn_bar_setting) })
        }
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP) {
            val v = currentFocus
            // 如果不是落在EditText区域，则需要关闭输入法
            if (KeyBoardUtil.isHideKeyboard(v, ev)) {
                KeyBoardUtil.hideKeyBoard(this, v)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        CommonUtil.fixInputMethodManagerLeak(this)
        BaseApp.getRefWatcher(this)?.watch(this)
    }


    override fun onToolbarClick(id: Int) {
        when (id) {
            R.id.tv_bar_back -> onToolbarBackText()
            R.id.ibtn_bar_back -> onToolbarBack()
            R.id.tv_bar_setting -> onToolbarSettingText()
            R.id.ibtn_bar_setting -> onToolbarSetting()
        }
    }

    open fun onToolbarBackText(){

    }
    open fun onToolbarBack(){
        this.finish()
    }
    open fun onToolbarSettingText(){

    }
    open fun onToolbarSetting(){

    }


}