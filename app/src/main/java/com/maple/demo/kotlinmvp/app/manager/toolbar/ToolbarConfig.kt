package com.maple.demo.kotlinmvp.app.manager.toolbar


/**
 * author: gaogq
 * time: 2018/12/26 11:39
 * description:
 */
class ToolbarConfig private constructor(val hasTitle: Boolean,
                                        val title: String?,
                                        val hasBackText: Boolean,
                                        val hasBack: Boolean,
                                        val backText: String?,
                                        val hasSettingText: Boolean,
                                        val hasSetting: Boolean,
                                        val settingText: String?,
                                        val listener: OnToolbarListener?

){


   //私有化构造方法
    private constructor(builder: Builder):this(builder.hasTitle,
            builder.title,
            builder.hasBackText,
            builder.hasBack,
            builder.backText,
            builder.hasSettingText,
            builder.hasSetting,
            builder.settingText,
            builder.listener
    ){}

    //伴生对象，对外提供静态的build方法
    companion object {
        fun build(init: Builder.() -> Unit) = Builder(init).build()
    }

    //Builder 内部类
    class Builder private constructor() {
        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        //属性
         var hasTitle: Boolean = true
         var title: String? = null
         var hasBackText: Boolean = false
         var hasBack: Boolean = true
         var backText: String? = null
         var hasSettingText: Boolean = false
         var hasSetting: Boolean = false
         var settingText: String? = null
         var listener: OnToolbarListener? = null

        //DSL赋值方法
        fun setHasTitle(init: Builder.() -> Boolean) = apply { hasTitle = init() }
        fun setTitle(init: Builder.() -> String) = apply { title = init() }
        fun setHasBackText(init: Builder.() -> Boolean) = apply { hasBackText = init() }
        fun setHasBack(init: Builder.() -> Boolean) = apply { hasBack = init() }
        fun setBackText(init: Builder.() -> String) = apply { backText = init() }
        fun setHasSettingText(init: Builder.() -> Boolean) = apply { hasSettingText = init() }
        fun setHasSetting(init: Builder.() -> Boolean) = apply { hasSetting = init() }
        fun setSettingText(init: Builder.() -> String) = apply { settingText = init() }
        fun setListener(init: Builder.() -> OnToolbarListener) = apply { listener = init() }

        fun build() = ToolbarConfig(this)
    }
}