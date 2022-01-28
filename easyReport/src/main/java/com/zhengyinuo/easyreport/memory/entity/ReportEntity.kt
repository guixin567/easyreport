package com.zhengyinuo.easyreport.memory.entity

data class ReportEntity<T> (var appVersion:String? = null,var datas:T? = null)