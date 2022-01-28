package com.zhengyinuo.easyreportexample.util

import android.content.Context

object  AppUtil {

    //APP版本
    fun getAppVersion(context: Context) = (context.packageManager?.getPackageInfo(context.packageName,0)?.versionName ?: "")

    //系统版本
    fun getSystemVersion(): String = (android.os.Build.VERSION.RELEASE ?: "")

    //系统名称
    fun getSystemModel(): String = (android.os.Build.MODEL ?: "")

}