package cn.yuan.baselibrary.log


/**
 * Created on 12/19/20
 * 这是一个日志的管理类
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class YLogManager private constructor(var config: YLogConfig) {


    companion object {
        /**
         * 示例(如果忘记初始化了,就会完犊子了骚年)
         */
        lateinit var instance: YLogManager

        /**
         * 只可以 从这里进行初始化
         * @param config YLogConfig
         */
        fun init(config: YLogConfig) {
            instance = YLogManager(config)
        }
    }

}