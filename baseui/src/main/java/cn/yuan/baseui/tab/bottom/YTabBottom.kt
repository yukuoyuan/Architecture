package cn.yuan.baseui.tab.bottom

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import cn.yuan.baseui.R
import cn.yuan.baseui.tab.common.IYTab


/**
 * Created on 12/25/20
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
class YTabBottom : RelativeLayout, IYTab<YTabBottomInfo<*>> {
    /**
     * 数据
     */
    private var mYTabBottomInfo: YTabBottomInfo<*>? = null

    /**
     * image icon
     */
    var mTabImageView: ImageView? = null

    /**
     * text icon
     */
    var mTabIconView: TextView? = null

    /**
     * name
     */
    var mTabNameView: TextView? = null

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initData(context)
    }

    private fun initData(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.y_tab_bottom, this)
        mTabImageView = findViewById(R.id.iv_y_tab_bottom_icon)
        mTabIconView = findViewById(R.id.tv_y_tab_bottom_icon)
        mTabNameView = findViewById(R.id.tv_y_tab_bottom_name)

    }

    override fun setYTabInfo(data: YTabBottomInfo<*>) {
        this.mYTabBottomInfo = data
        inflateInfo(isSelect = false, isInit = true)
    }

    /**
     * 填充
     * @param isSelect Boolean
     * @param isInit Boolean
     */
    private fun inflateInfo(isSelect: Boolean, isInit: Boolean) {
        /**
         * 如果是填充的iconfont 图标
         */
        if (mYTabBottomInfo?.tabType == YTabBottomInfo.TabType.ICON) {
            /**
             * 是否 是第一次填充
             */
            if (isInit) {
                mTabImageView?.visibility = View.GONE
                mTabIconView?.visibility = View.VISIBLE
                /**
                 * 设置图标
                 */
                val typeface = Typeface.createFromAsset(context.assets, mYTabBottomInfo?.iconFont)
                mTabIconView?.typeface = typeface
                /**
                 * 设置名字
                 */
                if (!TextUtils.isEmpty(mYTabBottomInfo?.name)) {
                    mTabNameView?.text = mYTabBottomInfo?.name
                }
            }

            if (isSelect) {
                /**
                 * 设置选中的icon
                 */
                mTabIconView?.text = if (TextUtils.isEmpty(mYTabBottomInfo?.selectIconName)) {
                    mYTabBottomInfo?.defaultIconName
                } else {
                    mYTabBottomInfo?.selectIconName
                }
                /**
                 * 设置选中的颜色
                 */
                mTabNameView?.setTextColor(getTextColor(mYTabBottomInfo?.selectColor))
                mTabIconView?.setTextColor(getTextColor(mYTabBottomInfo?.selectColor))
            } else {
                /**
                 * 设置选中的icon
                 */
                mTabIconView?.text = mYTabBottomInfo?.defaultIconName
                /**
                 * 设置选中的颜色
                 */
                mTabNameView?.setTextColor(getTextColor(mYTabBottomInfo?.defaultColor))
                mTabIconView?.setTextColor(getTextColor(mYTabBottomInfo?.defaultColor))
            }
        } else if (mYTabBottomInfo?.tabType == YTabBottomInfo.TabType.BITMAP) {
            /**
             * 是否 是第一次填充
             */
            if (isInit) {
                mTabImageView?.visibility = View.VISIBLE
                mTabIconView?.visibility = View.GONE
                /**
                 * 设置名字
                 */
                if (!TextUtils.isEmpty(mYTabBottomInfo?.name)) {
                    mTabNameView?.text = mYTabBottomInfo?.name
                }
            }
            if (isSelect) {
                /**
                 * 设置图标
                 */
                mYTabBottomInfo?.selectBitmap?.let { mTabImageView?.setImageResource(it) }
                /**
                 * 设置选中的颜色
                 */
                mTabNameView?.setTextColor(getTextColor(mYTabBottomInfo?.selectColor))
            } else {
                /**
                 * 设置图标
                 */
                mYTabBottomInfo?.defaultBitmap?.let { mTabImageView?.setImageResource(it) }
                /**
                 * 设置选中的颜色
                 */
                mTabNameView?.setTextColor(getTextColor(mYTabBottomInfo?.defaultColor))
            }
        }

    }

    override fun resetHeight(height: Int) {
        val mLayoutParams = layoutParams
        mLayoutParams.height = height
        layoutParams = mLayoutParams
        mTabNameView?.visibility = View.GONE
    }

    override fun onTabSelect(index: Int, preInfo: YTabBottomInfo<*>, nexInfo: YTabBottomInfo<*>) {
        if (preInfo != mYTabBottomInfo && nexInfo != mYTabBottomInfo || preInfo == nexInfo) {
            return
        }
        if (preInfo == mYTabBottomInfo) {
            inflateInfo(isSelect = false, isInit = false)
        } else {
            inflateInfo(isSelect = true, isInit = false)
        }
    }

    /**
     * 获取字体颜色
     * @param color Any
     * @return Int
     */
    @ColorInt
    fun getTextColor(color: Any?): Int {
        if (color is String) {
            return Color.parseColor(color)
        }
        return color as Int
    }

}