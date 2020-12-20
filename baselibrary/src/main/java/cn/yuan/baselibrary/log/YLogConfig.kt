package cn.yuan.baselibrary.log

import org.json.JSONObject


/**
 * Created on 12/19/20
 * 日志配置类
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
abstract class YLogConfig {
    companion object {
        /**
         * 每行日志的最大长度
         */
        const val LINE_MAX_LEN = 512

        /**
         * 线程打印格式化器的实例
         */
        val yThreadFormatter = YThreadFormatter()

        /**
         * 堆栈打印格式化器的实例
         */
        val yStackTraceFormatter = YStackTraceFormatter()
    }

    /**
     * 注入一个json的工具,例如 Gson 或者 fast json
     */
    open fun injectJsonParser(): JsonParser? {
        return null
    }

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

    /**
     * 是否包含线程信息
     * @return Boolean
     */
    open fun includeThread(): Boolean {
        return false
    }

    /**
     * 堆栈信息深度
     * @return Int 默认第五层
     */
    open fun stackTraceDepth(): Int {
        return 5
    }

    /**
     * 打印器
     * @return Array<YLogPrinter>?
     */
    open fun printers(): Array<YLogPrinter>? {
        return null
    }

    /**
     * json转换器
     */
    interface JsonParser {
        /**
         * 对象转换为一个字符串
         * @param src Any
         * @return String
         */
        fun toJson(src: Any): String
    }
}