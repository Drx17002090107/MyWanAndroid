package com.example.machenike.mywanandroid.ui.main.home

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.home.ArticleInfoModel
import com.example.machenike.mywanandroid.model.home.ArticlePageModel
import com.example.machenike.mywanandroid.model.home.Data

/**
created time：2019/12/19 10:19
created by：动感超人
Describe ：
 */
class ArticleAdapter():
    BaseQuickAdapter<ArticleInfoModel,BaseViewHolder>(R.layout.item_no_picture_ariticle){
//
    override fun convert(helper: BaseViewHolder, item: ArticleInfoModel) {
        helper.run {
            setGone(R.id.tvHomeAriticleNew,
                if(item.fresh)true else false)
            setGone(R.id.tvHomeAriticleTop,
                if(item.type==1)true else false)
            setText(R.id.tvHomeAriticleAuthor,
                if(!item.author.isEmpty())item.author else item.shareUser)
            setText(R.id.tvHomeAriticleTime,
                if(!item.niceShareDate.isEmpty())item.niceShareDate else item.niceDate )
            setText(R.id.tvHomeAriticleTitle,Html.fromHtml(item.title))
            setText(R.id.tvHomeAriticleType,"${item.superChapterName}·${item.chapterName}")
            setImageResource(R.id.imgHomeCollect,
                if(item.collect) R.drawable.ic_collect_selected else R.drawable.ic_collect_normal_gray)
            if(item.tags.isNotEmpty()){
                setText(R.id.tvHomeAriticleTag,item.tags[0].name)
                setGone(R.id.tvHomeAriticleTag,true)
            }
            addOnClickListener(R.id.imgHomeCollect)

        }

    }

}