package cn.yuan.baselibrary.log

import android.util.Log
import androidx.annotation.IntDef


/**
 * Created on 12/19/20
 * 日志类型类
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
object YLogType {

    @IntDef(V, D, I, A, W, E)
    @Retention(AnnotationRetention.SOURCE)
    annotation class TYPE

    const val V = Log.VERBOSE
    const val D = Log.DEBUG
    const val I = Log.INFO
    const val A = Log.ASSERT
    const val W = Log.WARN
    const val E = Log.ERROR
}