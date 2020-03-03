package com.example.machenike.mywanandroid.model.home

/**
created time：2019/12/19 15:33
created by：动感超人
Describe ：
 */
data class BannerModel(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)