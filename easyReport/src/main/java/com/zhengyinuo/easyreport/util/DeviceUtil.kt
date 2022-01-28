package com.zhengyinuo.easyreport.util

import android.content.Context


object  DeviceUtil {

    fun getAppVersion(context: Context) = (context.packageManager?.getPackageInfo(context.packageName,0)?.versionName ?: "")

    fun getSystemVersion(): String = (android.os.Build.VERSION.RELEASE ?: "")

    fun getSystemModel(): String = (android.os.Build.MODEL ?: "")

}
