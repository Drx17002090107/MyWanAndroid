package com.example.machenike.mywanandroid.ui.login.state

import android.content.Context

/**
created time：2020/1/3 10:30
created by：动感超人
Describe ：
 */
interface UserState {
    fun collect(context:Context?,block:()->Unit)

    fun login(context:Context?)
}