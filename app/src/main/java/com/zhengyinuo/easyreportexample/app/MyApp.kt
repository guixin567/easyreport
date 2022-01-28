package com.zhengyinuo.easyreportexample.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.zhengyinuo.easyreport.memory.core.EasyApp
import com.zhengyinuo.easyreport.memory.entity.MemoryEntity
import com.zhengyinuo.easyreportexample.util.AppUtil
import leakcanary.EventListener
import leakcanary.LeakCanary

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
        LeakCanary.config = LeakCanary.config.run {
            copy(
                eventListeners = eventListeners + EventListener { event ->
                    if (event is EventListener.Event.HeapAnalysisDone.HeapAnalysisSucceeded) {
                        val appVersion = AppUtil.getAppVersion(this@MyApp)
                        val osVersion = AppUtil.getSystemVersion()
                        val osName = AppUtil.getSystemModel()
                        val datas =  event.heapAnalysis.applicationLeaks.map {
                            MemoryEntity().apply {
                                signature = it.signature
                                this.appVersion = appVersion
                                this.osVersion = osVersion
                                this.osType = "Android"
                                this.osName = osName
                                this.name = it.leakTraces[0].leakingObject.classSimpleName
                                this.content = it.leakTraces.toString()
                            }
                        }
                        EasyApp.instance.report(datas)
                    }
                }
            )
        }
    }
}