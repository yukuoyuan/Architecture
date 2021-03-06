package cn.yuan.architecture.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.yuan.architecture.R
import cn.yuan.baselibrary.log.YLog
import cn.yuan.baselibrary.log.YLogManager
import cn.yuan.baselibrary.log.YViewPrinter

class LogDemoActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var printer: YViewPrinter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_demo)
        printer = YViewPrinter(this)
        YLogManager.mPrinters.add(printer)
        printer.logViewPrinterProvider.showFloatingView()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {
                YLog.a("2141")
            }
        }
    }
}