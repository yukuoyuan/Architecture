package cn.yuan.baseui.tab.bottom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import cn.yuan.baseui.tab.common.IYTabLayout


/**
 * Created on 2/18/21
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class YTabBottomLayout : FrameLayout, IYTabLayout<YTabBottom, YTabBottomInfo<*>> {
    /**
     * 当前选中的info
     */
    private val selectYTabBottomInfo: YTabBottomInfo<*>? = null

    /**
     * 背景的透明度
     */
    var bgAlpha: Int = 0

    /**
     * tab 高度 默认50dp
     */
    var mYTabBottomHeight = 50

    /**
     * 线条高度 默认0.5 dp
     */
    var mmYTabBottomTopLineHeight = 0.5f

    /**
     * 线条颜色
     */
    var mmYTabBottomTopLineColor = "#BBBBBB"

    /**
     * 信息集合
     */
    private var mYTabBottomInfoList: ArrayList<YTabBottomInfo<*>>? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initData(context)
    }

    private fun initData(context: Context) {

    }

    override fun findTab(data: YTabBottomInfo<*>): YTabBottom {
        return YTabBottom(context)
    }

    override fun addTabSelectListener(onTabSelectListener: IYTabLayout.OnTabSelectListener<YTabBottomInfo<*>>) {
    }

    override fun inflateInfo(mYTabBottomInfoList: ArrayList<YTabBottomInfo<*>>) {
        if (mYTabBottomInfoList.isNullOrEmpty()) {
            return
        }
        this.mYTabBottomInfoList = mYTabBottomInfoList
        /**
         * 防止重复添加数据
         */
    }
}