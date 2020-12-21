package cn.yuan.baselibrary.log

import android.util.Log


/**
 * Created on 12/20/20
 * 控制台打印器
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class YConsolePrinter : YLogPrinter {

    open override fun print(config: YLogConfig, type: Int, tag: String, printString: String) {
        /**
         * 总长度
         */
        val length = printString.length

        /**
         * 行数
         */
        val countLine = length / YLogConfig.LINE_MAX_LEN
        if (countLine > 0) {
            var index = 0
            for (i in 0 until countLine) {
                Log.println(
                    type,
                    tag,
                    printString.substring(index, index + YLogConfig.LINE_MAX_LEN)
                )
                index += YLogConfig.LINE_MAX_LEN
            }
            /**
             * 没有打印完毕,还多余一些
             */
            if (index != length) {
                Log.println(
                    type,
                    tag,
                    printString.substring(index, length)
                )
            }
        } else {
            Log.println(
                type,
                tag,
                printString
            )
        }
    }
}