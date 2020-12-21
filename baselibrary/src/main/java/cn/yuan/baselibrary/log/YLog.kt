package cn.yuan.baselibrary.log

import java.lang.StringBuilder


/**
 * Created on 12/19/20
 * 日志类
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
object YLog {
    /**
     * 包名
     */
    private val Y_LOG_PACKAGE_NAME = {
        val className = YLog.javaClass.name
        className.substring(0, className.lastIndexOf(".") + 1)
    }


    /**
     * 可以打印v类型的日志(不带tag)
     * @param contents Array<out Any>
     */
    fun v(vararg contents: Any) {
        log(YLogType.V, contents)
    }

    /**
     * 可以打印v类型的日志(带tag)
     * @param contents Array<out Any>
     */
    fun v(tag: String, vararg contents: Any) {
        log(YLogType.V, tag, contents)
    }

    /**
     * 可以打印d类型的日志(不带tag)
     * @param contents Array<out Any>
     */
    fun d(vararg contents: Any) {
        log(YLogType.D, contents)
    }

    /**
     * 可以打印d类型的日志(带tag)
     * @param contents Array<out Any>
     */
    fun d(tag: String, vararg contents: Any) {
        log(YLogType.D, tag, contents)
    }

    /**
     * 可以打印i类型的日志(不带tag)
     * @param contents Array<out Any>
     */
    fun i(vararg contents: Any) {
        log(YLogType.I, contents)
    }

    /**
     * 可以打印i类型的日志(带tag)
     * @param contents Array<out Any>
     */
    fun i(tag: String, vararg contents: Any) {
        log(YLogType.I, tag, contents)
    }

    /**
     * 可以打印a类型的日志(不带tag)
     * @param contents Array<out Any>
     */
    fun a(vararg contents: Any) {
        log(YLogType.A, contents)
    }

    /**
     * 可以打印a类型的日志(带tag)
     * @param contents Array<out Any>
     */
    fun a(tag: String, vararg contents: Any) {
        log(YLogType.A, tag, contents)
    }

    /**
     * 可以打印w类型的日志(不带tag)
     * @param contents Array<out Any>
     */
    fun w(vararg contents: Any) {
        log(YLogType.W, contents)
    }

    /**
     * 可以打印w类型的日志(带tag)
     * @param contents Array<out Any>
     */
    fun w(tag: String, vararg contents: Any) {
        log(YLogType.W, tag, contents)
    }

    /**
     * 可以打印e类型的日志(不带tag)
     * @param contents Array<out Any>
     */
    fun e(vararg contents: Any) {
        log(YLogType.E, contents)
    }

    /**
     * 可以打印e类型的日志(带tag)
     * @param contents Array<out Any>
     */
    fun e(tag: String, vararg contents: Any) {
        log(YLogType.E, tag, contents)
    }

    fun log(@YLogType.TYPE type: Int, vararg contents: Any) {
        log(type, YLogManager.instance.config.getGlobalTag(), contents)
    }

    fun log(@YLogType.TYPE type: Int, tag: String, vararg contents: Any) {
        log(YLogManager.instance.config, type, tag, contents)
    }

    fun log(config: YLogConfig, @YLogType.TYPE type: Int, tag: String, vararg contents: Any) {
        /**
         * 如果没有启用日志,就不要进行日志的打印了
         */
        if (!config.enable()) {
            return
        }
        val stringBuilder = StringBuilder()

        /**
         * 是否包含线程信息
         */
        if (config.includeThread()) {
            val threadInfo = YLogConfig.yThreadFormatter.format(Thread.currentThread())
            stringBuilder.append(threadInfo).append("\n")
        }
        /**
         * 是否打印堆栈信息
         */
        if (config.stackTraceDepth() > 0) {
            val stackTraceInfo = YLogConfig.yStackTraceFormatter.format(
                YStackTraceUtils.getClipRealStackTrace(
                    Throwable().stackTrace,
                    Y_LOG_PACKAGE_NAME(),
                    config.stackTraceDepth()
                )
            )
            stringBuilder.append(stackTraceInfo).append("\n")
        }
        val body = parseBody(contents, config)
        stringBuilder.append(body)

        /**
         * 获取打印器进行打印
         */
        val printers: ArrayList<YLogPrinter>? = if (config.printers().isNullOrEmpty()) {
            YLogManager.mPrinters
        } else {
            config.printers()
        }
        if (printers.isNullOrEmpty()) {
            return
        }
        for (printer in printers) {
            printer.print(config, type, tag, stringBuilder.toString())
        }
    }

    /**
     * 转换传递的数据为一个字符串
     * @param contents Array<out Any>
     */
    private fun parseBody(contents: Array<out Any>, config: YLogConfig): String {
        if (config.injectJsonParser() != null) {
            /**
             * 需要解析为json
             */
            return config.injectJsonParser()!!.toJson(contents)
        }

        val stringBuilder = StringBuilder()
        for (content in contents) {
            stringBuilder.append(content.toString()).append(";")
        }
        /**
         * 移除最后一个 ;
         */
        if (contents.size > 1) {
            stringBuilder.deleteCharAt(stringBuilder.length - 1)
        }
        return stringBuilder.toString()
    }
}