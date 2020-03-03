package com.example.machenike.mywanandroid.model.project

/**
created time：2019/12/26 10:02
created by：动感超人
Describe ：
 */
data class ProjectNamesModel(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)