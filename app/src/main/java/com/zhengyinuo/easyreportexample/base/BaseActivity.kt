package com.zhengyinuo.easyreportexample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    private lateinit var _binding: T
    protected val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(_binding.root)
        initView()
        initData()
        initListener()
    }

    //视图初始化
    open fun initView() {}

    //视图初始化
    open fun initData() {}

    //事件初始化
    open fun initListener() {}

    //获取视图绑定
    abstract fun getViewBinding(): T
}