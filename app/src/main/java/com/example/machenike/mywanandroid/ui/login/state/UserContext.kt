package com.example.machenike.mywanandroid.ui.login.state

import android.content.Context
import com.example.machenike.mywanandroid.utils.Preference

/**
created time：2020/1/3 10:48
created by：动感超人
Describe ： 状态模式
https://blog.csdn.net/weixin_40595516/article/details/103324190
 */
object UserContext {
    var isLogin by Preference(Preference.IS_LOGIN,false)

    var mState:UserState = if(isLogin) LoginState() else LoginOutState()

    fun collect(context:Context?,block:()->Unit){
        mState.collect(context, block)
    }

    fun login(context: Context?){
        mState.login(context)
    }

    fun setLoginState(){
        isLogin = true;
        mState = LoginState()
    }

    fun setLogoutState(){
        // 改变 sharedPreferences   isLogin值
        isLogin = false
        mState = LoginOutState()
    }
}