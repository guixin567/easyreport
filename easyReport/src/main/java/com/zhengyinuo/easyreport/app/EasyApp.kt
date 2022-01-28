package com.zhengyinuo.easyreport.app

import android.util.Log
import com.google.gson.Gson
import com.zhengyinuo.easyreport.config.Config.baseUrl
import com.zhengyinuo.easyreport.memory.entity.MemoryEntity
import com.zhengyinuo.easyreport.memory.entity.ReportEntity
import com.zhengyinuo.easyreport.util.noDelegate
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class EasyApp private constructor(){
    private val client by lazy { OkHttpClient() }


    companion object{
        val instance: EasyApp by lazy {
            EasyApp()
        }
    }

    //上报内存泄漏数据
    fun reportLeak(datas : List<MemoryEntity>) {
        val reportEntity = ReportEntity<List<MemoryEntity>>()

        reportEntity.datas = datas

        val toJson = Gson().toJson(reportEntity)
        val params  = JSONObject()
        params.put("report", JSONObject(toJson))
        params.put("appType", "1")
        val requestBody = RequestBody.create(MediaType.get("application/json"), params.toString())

        val url = "${baseUrl}/memory/report_leak"

        client.newCall(Request.Builder().url(url).post(requestBody).build()).enqueue(object:
            Callback by noDelegate() {
            override fun onResponse(call: Call, response: Response) {
                response.close()
                Log.e("easyreport","success $response")
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("easyreport","error $e")
            }

        })
    }
}