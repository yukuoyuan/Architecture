package cn.yuan.baseui.tab.common

import android.view.ViewGroup
import cn.yuan.baseui.tab.bottom.YTabBottomInfo


/**
 * Created on 2/17/21
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
interface IYTabLayout<Tab : ViewGroup, D> {
    /**
     * 找到tab
     * @param data D
     * @return Tab
     */
    fun findTab(data: D): Tab

    /**
     * 添加监听
     * @param onTabSelectListener OnTabSelectListener<D>
     */
    fun addTabSelectListener(onTabSelectListener: OnTabSelectListener<D>)

    /**
     * 填充数据
     * @param mYTabBottomInfoList ArrayList<YTabBottomInfo<*>>
     */
    fun inflateInfo(mYTabBottomInfoList: ArrayList<YTabBottomInfo<*>>)
    /**
     * tab 选中的监听
     * @param D 数据
     */
    interface OnTabSelectListener<D> {
        /**
         * tab 选中回调的方法
         * @param index Int
         * @param preInfo D
         * @param nexInfo D
         */
        fun onTabSelect(index: Int, preInfo: D, nexInfo: D)
    }
}