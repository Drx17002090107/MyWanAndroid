package com.example.machenike.mywanandroid.model.me

/**
created time：2019/12/18 11:12
created by：动感超人
Describe ：个人积分
 */
data class PersonalScore(
    val coinCount: Int,
    val level: Int,
    val rank: Int,
    val userId: Int,
    val username: String
)