package cn.yuan.baseui.tab.common


/**
 * Created on 12/25/20
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
interface IYTab<D> : IYTabLayout.OnTabSelectListener<D> {
    /**
     * 设置 tab 信息
     * @param data D
     */
    fun setYTabInfo(data: D)

    /**
     * 设置某个条目的高度
     * @param height Int
     */
    fun resetHeight(height: Int)
}