package com.example.machenike.mywanandroid.ui.main.me

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.ui.base.BaseVMActivity
import com.example.machenike.mywanandroid.ui.base.BaseVMFragment
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.GRAVITY_CENTER
import kotlinx.android.synthetic.main.activity_collect.*

class CollectActivity : BaseVMActivity<CollectViewModel>() {

    override fun providerVMClass(): Class<CollectViewModel> = CollectViewModel::class.java
    override fun getLayoutResId(): Int = R.layout.activity_collect

    val titleList = arrayOf("文章","网站")
    val fragmentList:Array<BaseVMFragment<out BaseViewModel>> = arrayOf(ArticleCollectFragment.newInstance())
    override fun initViewData(savedInstanceState: Bundle?) {
        tlAtricleCollectNavigation.tabGravity = GRAVITY_CENTER
        for(title in titleList)
            tlAtricleCollectNavigation.addTab(tlAtricleCollectNavigation.newTab().setText(title))

        vpCollectList.adapter = ViewpagerAdapter(supportFragmentManager)
        tlAtricleCollectNavigation.setupWithViewPager(vpCollectList)
    }
    inner class ViewpagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment = fragmentList[position]
        override fun getCount() = fragmentList.size
        override fun getPageTitle(position: Int) = titleList[position]
    }
    override fun startObserve() {
        super.startObserve()
    }
}
