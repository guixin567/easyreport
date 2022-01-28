package com.zhengyinuo.easyreportexample.util

import android.app.Activity
import android.content.Context

///测试单例
class TestSingleton private constructor(){
    var activity:Context? = null


    companion object{

        var context: Context? = null

        val instance by lazy {
            TestSingleton()
        }
    }
}