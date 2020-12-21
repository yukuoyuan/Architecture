package cn.yuan.architecture

import android.app.Application
import cn.yuan.baselibrary.log.YConsolePrinter
import cn.yuan.baselibrary.log.YLogConfig
import cn.yuan.baselibrary.log.YLogManager
import com.google.gson.Gson


/**
 * Created on 12/19/20
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class ArchitectureApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        YLogManager.init(object : YLogConfig() {
            override fun getGlobalTag(): String {
                return "test"
            }

            override fun enable(): Boolean {
                return true
            }

            override fun injectJsonParser(): JsonParser {
                return object : JsonParser {
                    override fun toJson(src: Any): String {
                        return Gson().toJson(src)
                    }
                }
            }

            override fun includeThread(): Boolean {
                return true
            }
        }, YConsolePrinter())
    }
}