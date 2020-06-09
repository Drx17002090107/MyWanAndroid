package com.example.machenike.mywanandroid.ui.main.systemNavigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.ui.base.BaseFragment
import com.example.machenike.mywanandroid.ui.main.navigation.NavigationFragment
import com.example.machenike.mywanandroid.ui.main.system.SystemFragment
import com.google.android.material.tabs.TabLayout.GRAVITY_CENTER
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import kotlinx.android.synthetic.main.fragment_system_navigation.*

/**
created time：2020/3/24 12:28
created by：动感超人
Describe ：
 */
class SystemNavigationFragment: BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_system_navigation
    val titleList = arrayOf("体系","导航")
    val fragmentList = arrayOf(SystemFragment.newInstance(),
        NavigationFragment.newInstance())
    override fun initViewData(view: View, savedInstanceState: Bundle?) {
        tlSystemNavigationMain.tabGravity = GRAVITY_CENTER
//        tlSystemNavigationMain.tabMode = MODE_SCROLLABLE

        for (title in titleList)
        tlSystemNavigationMain.addTab(tlSystemNavigationMain.newTab().setText(title))

        vpSystemNavigationContent.adapter = ViewpagerAdapter(childFragmentManager)
        tlSystemNavigationMain.setupWithViewPager(vpSystemNavigationContent)
    }

    inner class ViewpagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment = fragmentList[position]
        override fun getCount() = fragmentList.size
        override fun getPageTitle(position: Int) = titleList[position]
    }
    companion object {
        @JvmStatic
        fun newInstance() = SystemNavigationFragment()
    }
}