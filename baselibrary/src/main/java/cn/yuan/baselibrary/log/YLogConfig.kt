package cn.yuan.baselibrary.log


/**
 * Created on 12/19/20
 * 日志配置类
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
abstract class YLogConfig {
    /**
     * 获取全局的tag(默认tag为YLog)
     */
    open fun getGlobalTag(): String {
        return "YLog"
    }

    /**
     * 日志打印的开关(默认开启)
     * @return Boolean
     */
    open fun enable(): Boolean {
        return true
    }
}