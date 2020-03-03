package com.example.machenike.mywanandroid.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @description:
 * @author: litrainy
 * @create: 2019-08-27 15:53
 **/
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initViewData(savedInstanceState)
    }

    abstract fun getLayoutResId(): Int

    abstract fun initViewData(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}