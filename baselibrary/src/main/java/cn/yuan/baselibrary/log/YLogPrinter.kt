package cn.yuan.baselibrary.log


/**
 * Created on 12/20/20
 * 日志打印的抽象类
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
interface YLogPrinter {
    /**
     * 打印方法
     * @param config YLogConfig 配置
     * @param type Int 类型
     * @param tag String 标签
     * @param printString String 打印的内容
     */
    fun print(config: YLogConfig, @YLogType.TYPE type: Int, tag: String, printString: String)
}