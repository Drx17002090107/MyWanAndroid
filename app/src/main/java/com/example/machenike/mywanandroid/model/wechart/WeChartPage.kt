package com.example.machenike.mywanandroid.model.wechart

/**
created time：2020/1/2 16:43
created by：动感超人
Describe ：
 */
data class WeChartPage(
    val curPage: Int,
    val datas: List<WeChartInfo>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
data class Tag(
    val name: String,
    val url: String
)