package cn.yuan.baselibrary.log


/**
 * Created on 12/20/20
 * 线程打印器
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class YThreadFormatter : YLogFormatter<Thread> {

    override fun format(data: Thread): String {
        return "Thread:${data.name}"
    }
}