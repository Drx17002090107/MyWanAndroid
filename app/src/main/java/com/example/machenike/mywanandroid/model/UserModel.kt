package com.example.machenike.mywanandroid.net.response.login

/**
created time：2019/12/12 16:42
created by：动感超人
Describe ：
 */
data class UserModel(
    /**
     * {
    "admin": false,
    "chapterTops": [],
    "collectIds": [],
    "email": "",
    "icon": "",
    "id": 38807,
    "nickname": "850167592",
    "password": "",
    "publicName": "850167592",
    "token": "",
    "type": 0,
    "username": "850167592"
    }
     */
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)