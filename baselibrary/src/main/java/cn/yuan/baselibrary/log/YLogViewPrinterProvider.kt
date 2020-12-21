package cn.yuan.baselibrary.log

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


/**
 * Created on 12/21/20
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class YLogViewPrinterProvider(
    private val rootView: FrameLayout,
    private val recyclerView: RecyclerView
) {
    /**
     * 是否打开
     */
    private var isOpen = false

    companion object {
        /**
         * 展示日志的控件的标签
         */
        const val TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW"

        /**
         * 展示日志内容输出控件的标签
         */
        const val TAG_LOG_VIEW = "TAG_LOG_VIEW"
    }

    /**
     * 打开或者关闭日志输出控件的按钮
     */
    private var floatingView: TextView? = null

    /**
     * 日志输出控件
     */
    private var logPrinterView: FrameLayout? = null

    /**
     * 展示按钮
     */
    fun showFloatingView() {
        /**
         * 如果已经有该控件的话,那么就直接返回
         */
        if (rootView.findViewWithTag<TextView>(TAG_FLOATING_VIEW) != null) {
            return
        }
        /**
         * 宽度和高度都为包括内容
         */
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
        layoutParams.bottomMargin = dip2px(100)
        val floatingView = getFloatingView()
        rootView.addView(floatingView, layoutParams)
    }

    private fun closeFloatingView() {
        rootView.removeView(getFloatingView())
    }

    /**
     * 获取按钮控件
     * @return View
     */
    private fun getFloatingView(): View {
        if (floatingView != null) {
            return floatingView!!
        }
        floatingView = TextView(rootView.context).apply {
            setOnClickListener {
                closeFloatingView()
                showLogPrinterView()
            }
            tag = TAG_FLOATING_VIEW
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.BLACK)
            text = "YLOG"
        }

        return floatingView!!
    }

    /**
     * 展示按钮
     */
    private fun showLogPrinterView() {
        isOpen = true
        /**
         * 如果已经有该控件的话,那么就直接返回
         */
        if (rootView.findViewWithTag<TextView>(TAG_LOG_VIEW) != null) {
            return
        }
        /**
         * 宽度和高度都为包括内容
         */
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            dip2px(200)
        )
        layoutParams.gravity = Gravity.BOTTOM
        val logPrinterView = getLogPrinterView()
        rootView.addView(logPrinterView, layoutParams)
    }

    /**
     * 获取日志输出控件
     * @return View
     */
    private fun getLogPrinterView(): View {
        if (logPrinterView != null) {
            return logPrinterView!!
        }
        val closeView = TextView(rootView.context).apply {
            text = "Close"
            setOnClickListener {
                closeLogPrinterView()
                showFloatingView()
            }
        }
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        val recyclerViewLayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.gravity = Gravity.END
        logPrinterView = FrameLayout(rootView.context)
            .apply {
                tag = TAG_LOG_VIEW
                setBackgroundColor(Color.BLACK)
                addView(recyclerView, recyclerViewLayoutParams)
                addView(closeView, layoutParams)
            }

        return logPrinterView!!
    }

    /**
     * 关闭输出平台控件
     */
    private fun closeLogPrinterView() {
        isOpen = false
        rootView.removeView(getLogPrinterView())
    }
}