package com.example.machenike.mywanandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.utils.MyUtils
import kotlinx.android.synthetic.main.activity_browser_normal.*
import kotlinx.android.synthetic.main.head_normal.*

class BrowserNormalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser_normal)
        MyUtils.setStatusBarColor(this, R.color.blue)
        val title = intent.extras?.getString("title","")
        val url =intent.extras?.getString("url","")
        tvHeadTitle.text = title
        imgHeadBack.setOnClickListener{
            finish()
        }
        wvBrowser.loadUrl(url)

    }
}
