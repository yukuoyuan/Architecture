package cn.yuan.baselibrary.log


/**
 * Created on 12/21/20
 * 堆栈信息的工具类
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
object YStackTraceUtils {
    /**
     * 获取裁剪和忽略后打堆栈信息
     * @param stackTraces Array<StackTraceElement>
     * @param ignorePackage String
     * @param maxDepth Int
     * @return Array<StackTraceElement>
     */
    fun getClipRealStackTrace(
        stackTraces: Array<StackTraceElement>, ignorePackage: String,
        maxDepth: Int
    ): Array<StackTraceElement> {
        return clipStackTrace(getRealStackTrace(stackTraces, ignorePackage), maxDepth)
    }

    /**
     * 裁剪堆栈信息
     * @param stackTraces Array<StackTraceElement> 堆栈信息
     * @param maxDepth Int 最大深度
     * @return Array<StackTraceElement> 裁剪后的堆栈信息
     */
    private fun clipStackTrace(
        stackTraces: Array<StackTraceElement>,
        maxDepth: Int
    ): Array<StackTraceElement> {
        var realLength = stackTraces.size
        if (realLength > 0) {
            realLength = realLength.coerceAtMost(maxDepth)
        }
        val realStackTraces = Array(realLength) { StackTraceElement("", "", "", 0) }
        System.arraycopy(stackTraces, 0, realStackTraces, 0, realLength)
        return realStackTraces
    }

    /**
     * 获取真实的堆栈信息
     * @param stackTraces Array<StackTraceElement> 所有的堆栈信息
     * @param ignorePackage String 忽略的包名
     * @return Array<StackTraceElement> 真实的堆栈信息
     */
    private fun getRealStackTrace(
        stackTraces: Array<StackTraceElement>,
        ignorePackage: String
    ): Array<StackTraceElement> {
        var ignoreDepth = 0
        var realLength = stackTraces.size
        var className: String
        for (i in (realLength - 1) downTo 0) {
            className = stackTraces[i].className
            /**
             * 如果包名是需要忽略的
             */
            if (className.startsWith(ignorePackage)) {
                ignoreDepth = i + 1
                break
            }
        }
        realLength -= ignoreDepth
        val realStackTraces = Array(realLength) { StackTraceElement("", "", "", 0) }
        System.arraycopy(stackTraces, ignoreDepth, realStackTraces, 0, realLength)
        return realStackTraces
    }
}