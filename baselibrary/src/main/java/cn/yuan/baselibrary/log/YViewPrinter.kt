package cn.yuan.baselibrary.log

import android.app.Activity
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created on 12/21/20
 * 可视化的打印器
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class YViewPrinter(activity: Activity) : YLogPrinter {
    /**
     * 列表控件
     */
    var recyclerView: RecyclerView

    /**
     * 列表适配器
     */
    var adapter: LogViewListAdapter

    /**
     *视图提供者
     */
    var logViewPrinterProvider: YLogViewPrinterProvider

    init {
        val rootView = activity.findViewById<FrameLayout>(android.R.id.content)
        recyclerView = RecyclerView(activity)
        adapter = LogViewListAdapter(activity)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        logViewPrinterProvider = YLogViewPrinterProvider(rootView, recyclerView)
    }

    override fun print(config: YLogConfig, type: Int, tag: String, printString: String) {
        /**
         * 增加数据
         */
        adapter.addItem(LogViewBean(System.currentTimeMillis(), type, tag, printString))
        /**
         * 滚动到最后
         */
        recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
    }
}