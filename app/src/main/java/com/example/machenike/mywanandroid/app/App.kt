package com.example.machenike.mywanandroid.app

import android.app.Application
import android.content.Context
import com.coder.zzq.smartshow.core.SmartShow
import com.example.machenike.mywanandroid.net.response.login.UserModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlin.properties.Delegates


/**
created time：2019/12/12 9:25
created by：动感超人
Describe ：
 */
class App : Application() {
    companion object{
        var CONTEXT:Context by Delegates.notNull()
        lateinit var CURRENT_USER:UserModel
    }
    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        SmartShow.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}