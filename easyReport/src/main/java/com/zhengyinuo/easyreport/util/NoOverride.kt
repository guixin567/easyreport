package com.zhengyinuo.easyreport.util

import java.lang.reflect.InvocationHandler


import java.lang.reflect.Proxy

internal inline fun <reified T> noDelegate():T{
    val tclass = T::class.java
    return Proxy.newProxyInstance(tclass.classLoader, arrayOf(tclass), handle) as T
}

 val handle = InvocationHandler { proxy, method, args ->      }