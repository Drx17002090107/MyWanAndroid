package com.example.machenike.mywanandroid.ui.login.state

import android.content.Context

/**
created time：2020/1/3 10:33
created by：动感超人
Describe ：
 */
class LoginState : UserState {
    override fun collect(context: Context?, block: () -> Unit) {
        block()
    }

    override fun login(context: Context?) {}
}