package cn.yuan.baselibrary.log


/**
 * Created on 12/20/20
 * 格式化器
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
interface YLogFormatter<T> {
    fun format(data: T): String
}