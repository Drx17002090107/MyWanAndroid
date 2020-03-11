package com.example.machenike.mywanandroid.ui.main.wechart

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.wechart.WeChartInfo
import com.example.machenike.mywanandroid.utils.ChartUtils

/**
created time：2020/1/3 8:45
created by：动感超人
Describe ：
 */
class WeChartAdapter: BaseQuickAdapter<WeChartInfo, BaseViewHolder>(R.layout.item_no_picture_ariticle) {
    override fun convert(helper: BaseViewHolder, item: WeChartInfo) {
        helper.run {
            setGone(R.id.tvHomeAriticleNew,
                if(item.fresh)true else false)
            setGone(R.id.tvHomeAriticleTop,
                if(item.type==1)true else false)
            setText(R.id.tvHomeAriticleAuthor,
                if(!item.author.isEmpty())item.author else item.shareUser)
            setText(R.id.tvHomeAriticleTime,
                if(!item.niceShareDate.isEmpty())item.niceShareDate else item.niceDate )
            setText(R.id.tvHomeAriticleTitle, ChartUtils.replaceQuotationMarks(item.title))
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