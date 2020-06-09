package com.example.machenike.mywanandroid.utils

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.core.content.ContextCompat

/**
created time：2020/3/17 19:42
created by：动感超人
Describe ：
 */
object MyUtils {
    /**
     * 状态栏变色
     * @param activity
     * @param colorId
     */
     fun setStatusBarColor(activity: Activity, colorId:Int){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val window = activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity,colorId)//activity.resources.getColor(colorId)
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
        }
    }
}