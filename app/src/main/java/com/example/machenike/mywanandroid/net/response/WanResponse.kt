package com.example.machenike.mywanandroid.net.response

/**
created time：2019/12/12 10:26
created by：动感超人
Describe ：
 */
data class WanResponse<out T>(val errorCode:Int,val errorMsg:String,val data:T)