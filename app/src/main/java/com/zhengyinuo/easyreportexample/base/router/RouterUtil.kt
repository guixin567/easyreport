package com.zhengyinuo.easyreportexample.base.router

import com.alibaba.android.arouter.launcher.ARouter

class RouterUtil private constructor(){

    companion object{
        val instance by lazy {
            RouterUtil()
        }
    }

    //到内存页面
    fun toMemoryPage(){
        ARouter.getInstance().build(Paths.memory).navigation()
    }
}