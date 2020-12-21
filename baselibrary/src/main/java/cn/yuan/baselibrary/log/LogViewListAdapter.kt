package cn.yuan.baselibrary.log

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.yuan.baselibrary.R


/**
 * Created on 12/21/20
 * 日志可视化的列表适配器
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class LogViewListAdapter(private val context: Context) :
    RecyclerView.Adapter<LogViewListAdapter.LogViewHolder>() {
    /**
     *  日志数据类
     */
    var logListData = ArrayList<LogViewBean>()

    inner class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * 初始化数据到界面
         * @param position Int
         */
        fun setData2View(position: Int) {
            val logData = logListData[position]
            val tvItemLogViewListTag =
                itemView.findViewById<TextView>(R.id.tv_item_log_view_list_tag)
            val tvItemLogViewListMessage =
                itemView.findViewById<TextView>(R.id.tv_item_log_view_list_message)
            /**
             * 设置内容
             */
            tvItemLogViewListTag.text = logData.getFlattened()
            tvItemLogViewListMessage.text = logData.message

            /**
             * 设置颜色
             */
            val color = getLogColor(logData.level)
            tvItemLogViewListTag.setTextColor(Color.parseColor(color))
            tvItemLogViewListMessage.setTextColor(Color.parseColor(color))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(
            R.layout.item_log_view_list,
            parent,
            false
        )
        return LogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.setData2View(position)
    }

    override fun getItemCount(): Int {
        return logListData.size
    }

    fun getLogColor(level: Int): String {
        var color = ""
        when (level) {
            YLogType.V -> {
                color = "#5394EC"
            }
            YLogType.D -> {
                color = "#299999"
            }
            YLogType.I -> {
                color = "#ABC023"
            }
            YLogType.A -> {
                color = "#555555"
            }
            YLogType.W -> {
                color = "#299999"
            }
            YLogType.E -> {
                color = "#CC666E"
            }
        }
        return color
    }

    /**
     * 添加一条数据
     * @param logData LogViewBean
     */
    fun addItem(logData: LogViewBean) {
        logListData.add(logData)
        notifyItemInserted(logListData.size - 1)
    }
}