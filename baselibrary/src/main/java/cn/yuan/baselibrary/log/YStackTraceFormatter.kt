package cn.yuan.baselibrary.log

import java.lang.StringBuilder


/**
 * Created on 12/20/20
 * 堆栈信息格式化器
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class YStackTraceFormatter : YLogFormatter<Array<StackTraceElement>> {

    override fun format(data: Array<StackTraceElement>): String {
        return when {
            data.isNullOrEmpty() -> {
                ""
            }
            data.size == 1 -> {
                "\t- ${data[0].toString()}"
            }
            else -> {
                val stringBuilder = StringBuilder()
                for (i in data.indices) {
                    if (i == 0) {
                        stringBuilder.append("stackTrace: \n")
                    }
                    when {
                        i != (data.size - 1) -> {
                            stringBuilder.append("\t|- ")
                            stringBuilder.append(data[i].toString())
                            stringBuilder.append("\n")
                        }
                        else -> {
                            stringBuilder.append("\tL ")
                            stringBuilder.append(data[i].toString())
                        }
                    }
                }
                stringBuilder.toString()
            }
        }
    }
}