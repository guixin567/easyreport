package com.zhengyinuo.easyreportexample
import com.zhengyinuo.easyreportexample.base.BaseActivity
import com.zhengyinuo.easyreportexample.base.router.RouterUtil
import com.zhengyinuo.easyreportexample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initListener() {
        binding.btMemory.setOnClickListener {
            RouterUtil.instance.toMemoryPage()
        }
    }
}