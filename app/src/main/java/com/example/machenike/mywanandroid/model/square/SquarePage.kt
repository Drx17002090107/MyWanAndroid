package com.example.machenike.mywanandroid.model.square

/**
created time：2020/3/5 10:07
created by：动感超人
Describe ：
 */
data class SquarePage(
    val curPage: Int,
    val datas: List<SquareInfo>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

