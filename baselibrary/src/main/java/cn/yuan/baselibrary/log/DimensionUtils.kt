package cn.yuan.baselibrary.log

import android.content.Context
import android.content.res.Resources


/**
 * Created on 2020/10/26
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
fun dip2px(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun px2Dip(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

fun getStatusHeight(context: Context): Int {
    var statusHeight = -1
    try {
        val clazz = Class.forName("com.android.internal.R\$dimen")
        val `object` = clazz.newInstance()
        val height = clazz.getField("status_bar_height")[`object`].toString().toInt()
        statusHeight = context.resources.getDimensionPixelSize(height)
    } catch (var6: Exception) {
        var6.printStackTrace()
    }
    return statusHeight
}
