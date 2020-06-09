package com.example.machenike.mywanandroid.ui.main.square

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.square.SquareInfo

/**
created time：2020/3/5 10:20
created by：动感超人
Describe ：
 */
class SquareAdapter():
    BaseQuickAdapter<SquareInfo,BaseViewHolder>(R.layout.item_no_picture_ariticle){
    override fun convert(helper: BaseViewHolder, item: SquareInfo) {
        helper.run {
            setGone(R.id.tvHomeAriticleNew,
                if(item.fresh)true else false)
            setText(R.id.tvHomeAriticleAuthor,
                if(!item.author.isEmpty())item.author else item.shareUser)
            setText(R.id.tvHomeAriticleTime,
                if(!item.niceShareDate.isEmpty())item.niceShareDate else item.niceDate )
            setText(R.id.tvHomeAriticleTitle, Html.fromHtml(item.title))
            setText(R.id.tvHomeAriticleType,"${item.superChapterName}·${item.chapterName}")
            setImageResource(R.id.imgHomeCollect,
                if(item.collect) R.drawable.ic_collect_selected else R.drawable.ic_collect_normal_gray)
            addOnClickListener(R.id.imgHomeCollect)

        }
    }
}