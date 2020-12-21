package cn.yuan.baselibrary.log

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created on 12/21/20
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
data class LogViewBean(
    var time: Long = 0L,
    var level: Int = YLogType.I,
    var tag: String = "",
    var message: String = ""
) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)

    fun getFlattened(): String {
        return "${format(time)} | $level | ${tag}|:"
    }

    private fun format(time: Long): String? {
        return dateFormat.format(time)
    }
}
