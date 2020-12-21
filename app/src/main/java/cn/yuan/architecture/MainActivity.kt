package cn.yuan.architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cn.yuan.architecture.demo.LogDemoActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(view: View) {
        startActivity(Intent(this, LogDemoActivity::class.java))
    }
}