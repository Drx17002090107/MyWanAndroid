package com.example.machenike.mywanandroid.ui.main.me

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.me.ArticleCollect
import com.example.machenike.mywanandroid.model.me.Data

/**
created time：2020/5/6 16:33
created by：动感超人
Describe ：
 */
class ArticleCollectAdapter:
BaseQuickAdapter<Data,BaseViewHolder>(R.layout.item_no_picture_ariticle){
    override fun convert(helper: BaseViewHolder, item: Data) {
        helper.run {
            setText(R.id.tvHomeAriticleAuthor, item.author)
            setText(R.id.tvHomeAriticleTime,item.niceDate )
            setText(R.id.tvHomeAriticleTitle, Html.fromHtml(item.title))
            setText(R.id.tvHomeAriticleType,"${item.chapterName}")
            setImageResource(R.id.imgHomeCollect, R.drawable.ic_collect_selected )
            setGone(R.id.tvHomeAriticleTag,false)
            setGone(R.id.tvHomeAriticleTop,false)
            addOnClickListener(R.id.imgHomeCollect)
        }
    }
}