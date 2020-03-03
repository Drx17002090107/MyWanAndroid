package com.example.machenike.mywanandroid.model.wechart

/**
created time：2020/1/2 16:33
created by：动感超人
Describe ：
 */
data class WeChartNames(
    val children: List<String>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)