package cn.yuan.architecture.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.yuan.architecture.R
import cn.yuan.baselibrary.log.YLog
import cn.yuan.baselibrary.log.YLogManager
import cn.yuan.baselibrary.log.YViewPrinter
import cn.yuan.baseui.tab.bottom.YTabBottom
import cn.yuan.baseui.tab.bottom.YTabBottomInfo

class LogDemoActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var printer: YViewPrinter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_demo)
        printer = YViewPrinter(this)
        YLogManager.mPrinters.add(printer)
        printer.logViewPrinterProvider.showFloatingView()

        val ytbTest = findViewById<YTabBottom>(R.id.ytb_test);
        val tabBottomInfo =
            YTabBottomInfo(
                name = "首页",
                selectColor = "#ff0000",
                defaultColor = "#000000",
                tabType = YTabBottomInfo.TabType.BITMAP,
                selectBitmap = R.mipmap.ic_launcher,
                defaultBitmap = R.mipmap.ic_launcher_round
            )
        ytbTest.setYTabInfo(tabBottomInfo)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {
                YLog.a("2141")
            }
        }
    }
}