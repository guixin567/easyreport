## 开始

### 依赖导入

build.gradle

`implementation 'com.github.guixin567:easyreport:1.0'`

setting.gradle

```
repositories{
		...
    maven{url 'https://jitpack.io'}
}
```

### 代码接入

内存泄漏上报

```kotlin
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
												///将泄漏的数据上报到后台
                        EasyApp.instance.report(datas)
                    }
                }
            )
        }
```

### 数据展示

example 泄漏场景

![app_leak.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e3985fd6-1e0b-4aee-b577-efacadb4e72f/app_leak.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220128%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220128T044638Z&X-Amz-Expires=86400&X-Amz-Signature=cd916574ff73bc60c4c66252eed90e6a3ecfafddee82ed79eeb30e0b3c167036&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22app_leak.png%22&x-id=GetObject)

kibana 内存泄漏

![kibana1.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4b2c8370-4c70-4980-8b46-e09f961ee0ab/kibana1.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220128%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220128T044735Z&X-Amz-Expires=86400&X-Amz-Signature=7b6c1bbf33639abca672352529d0a3a054a8d6bee0c42863f6f1f1aa1763e544&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22kibana1.png%22&x-id=GetObject)
