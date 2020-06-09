package com.example.machenike.mywanandroid.ui.main.system

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.system.Children
import com.example.machenike.mywanandroid.model.system.SystemModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout


/**
created time：2020/3/19 21:40
created by：动感超人
Describe ：
 */
class SystemAdapter : BaseQuickAdapter<SystemModel, BaseViewHolder>(R.layout.item_system) {
    //多布局
//    public TestLayoutAdapter(List<TestLayoutBean> data) {
//        super(data);
//        //设置当传入的itemType为某个常量显示不同的item
//        addItemType(TestLayoutBean.EDIT, R.layout.item_list_edit);
//        addItemType(TestLayoutBean.CHECK,R.layout.item_list_check);
//        addItemType(TestLayoutBean.SELECT,R.layout.item_list_select);
//    }

    override fun convert(helper: BaseViewHolder, item: SystemModel) {
        helper.setText(R.id.itemSystemTitle,item.name)
        helper.getView<TagFlowLayout>(R.id.itemSystemContent).run {
            adapter = object : TagAdapter<Children>(item.children){
                override fun getCount(): Int {
                    return item.children.size
                }

                override fun getView(parent: FlowLayout, position: Int, t: Children): View {
                    val tv = LayoutInflater.from(parent.context).inflate(R.layout.item_system_chip,
                        parent,false) as TextView
                    tv.text = t.name
                    return tv
                }

            }
        }

    }


}