package com.example.machenike.mywanandroid.ui.main.navigation

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.navigation.Article
import com.example.machenike.mywanandroid.model.navigation.NavigationModel
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

/**
created time：2020/3/24 11:26
created by：动感超人
Describe ：
 */
class NavigationAdapter :BaseQuickAdapter<NavigationModel,BaseViewHolder>(R.layout.item_system) {
    override fun convert(helper: BaseViewHolder, item: NavigationModel) {
        helper.setText(R.id.itemSystemTitle,item.name)
        helper.getView<TagFlowLayout>(R.id.itemSystemContent).run {
            adapter = object : TagAdapter<Article>(item.articles){
                override fun getCount(): Int {
                    return item.articles.size
                }

                override fun getView(parent: FlowLayout, position: Int, t: Article): View {
                    val tv = LayoutInflater.from(parent.context).inflate(R.layout.item_system_chip,
                        parent,false) as TextView
                    tv.text = Html.fromHtml(t.title)
                    return tv
                }

            }
        }
    }

}