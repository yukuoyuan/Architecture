package cn.yuan.baseui.tab.bottom

import androidx.fragment.app.Fragment


/**
 * Created on 2/17/21
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
data class YTabBottomInfo<TabColor>(
    var fragment: Fragment? = null,
    var name: String,
    var defaultBitmap: Int = 0,
    var selectBitmap: Int = 0,
    var iconFont: String = "",
    var defaultIconName: String = "",
    var selectIconName: String = "",
    var defaultColor: TabColor,
    var selectColor: TabColor,
    var tabType: TabType,
) {
    enum class TabType {
        BITMAP, ICON
    }
}