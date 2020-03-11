package com.example.machenike.mywanandroid.utils

/**
created time：2020/3/8 21:10
created by：动感超人
Describe ：
 */
object ChartUtils {
    fun replaceQuotationMarks(str:String):String{
        return str.replace("&ldquo;","“")
            .replace("&rdquo;","”")
    }
}