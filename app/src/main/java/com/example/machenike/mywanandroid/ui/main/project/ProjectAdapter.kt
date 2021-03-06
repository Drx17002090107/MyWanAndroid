package com.example.machenike.mywanandroid.ui.main.project

import android.text.Html
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.app.App
import com.example.machenike.mywanandroid.model.project.ProjectInfoModel

/**
created time：2020/1/2 14:19
created by：动感超人
Describe ：
 */
class ProjectAdapter :BaseQuickAdapter<ProjectInfoModel,BaseViewHolder>(R.layout.item_picture_ariticle){
    override fun convert(helper: BaseViewHolder, item: ProjectInfoModel) {
        helper.setText(R.id.tvProjectAriticleAuthor,
            if(!item.author.isEmpty())item.author else item.shareUser)
            .setText(R.id.tvProjectAriticleTime,
                if(!item.niceShareDate.isEmpty())item.niceShareDate else item.niceDate )
            .setText(R.id.tvProjectAriticleTitle, Html.fromHtml(item.title))
            .setText(R.id.tvProjectAriticleDesc,Html.fromHtml(item.desc))
            .setText(R.id.tvProjectAriticleType,"${item.superChapterName}·${item.chapterName}")
            .setImageResource(R.id.imgProjectCollect,
                if(item.collect) R.drawable.ic_collect_selected else R.drawable.ic_collect_normal_gray)
        helper.addOnClickListener(R.id.imgProjectCollect)
        Glide.with(mContext).load(item.envelopePic).into(helper.getView(R.id.imgProject))

    }
}