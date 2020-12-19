package cn.yuan.architecture

import android.app.Application
import cn.yuan.baselibrary.log.YLogConfig
import cn.yuan.baselibrary.log.YLogManager


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
        })
    }
}