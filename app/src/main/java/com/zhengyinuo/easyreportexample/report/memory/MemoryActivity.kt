package com.zhengyinuo.easyreportexample.report.memory

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhengyinuo.easyreportexample.base.BaseActivity
import com.zhengyinuo.easyreportexample.base.router.Paths
import com.zhengyinuo.easyreportexample.databinding.ActivityMemoryBinding
import com.zhengyinuo.easyreportexample.util.TestSingleton


///常见内存泄漏 上报
@Route(path = Paths.memory)
class MemoryActivity : BaseActivity<ActivityMemoryBinding>() {

    private val mHandler by lazy { Handler(Looper.getMainLooper()) }

    override fun getViewBinding()= ActivityMemoryBinding.inflate(layoutInflater)


    override fun initListener() {
        //单例 造成内存泄漏
        binding.btSingle.setOnClickListener {
            TestSingleton.instance.activity = this
            //修复 使用application 或者 在使用的时候用WeakReference包一层
//            TestSingleton.instance.activity = this.application
        }

        //静态变量 造成内存泄漏
        binding.btStatic.setOnClickListener {
            TestSingleton.context = this
            //修复 使用application 或者 在使用的时候用WeakReference包一层
//            TestSingleton.context = this.application
        }

        //非静态内部类 和 匿名内部类 做耗时操作
        binding.btInnerClass.setOnClickListener {
            mHandler.postDelayed({
                Log.e("LeakCanary","内部类测试${this}")
            },30000)
        }

        //类似 Rxbus这种未注销造成的内存泄漏 这种可以用LiveBus,FlowBus
        binding.btRxbus.setOnClickListener {

        }


        //Handler 造成内存泄漏
        binding.btHandle.setOnClickListener {

        }
    }


}