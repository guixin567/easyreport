package com.zhengyinuo.easyreport.memory.entity

import com.zhengyinuo.easyreport.memory.entity.BaseReportEntity

class MemoryEntity: BaseReportEntity() {
     var signature: String? = null          //签名 判断是否是同一个内存泄漏
     var name: String? = null               //泄漏类名
     var content: String? = null            //泄漏的内容
 }

