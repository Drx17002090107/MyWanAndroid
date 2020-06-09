package com.example.machenike.mywanandroid.ui.main.systemNavigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*
import kotlin.collections.ArrayList

/**
created time：2020/3/24 12:33
created by：动感超人
Describe ：
 */
class SystemViewPagerAdapter: FragmentStateAdapter {
    var mFragmentList: MutableList<Fragment> = ArrayList()

    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)
    constructor(fragment: Fragment) : super(fragment)
    constructor(fragment: Fragment,fragmentList:MutableList<Fragment>) : super(fragment){
        mFragmentList = fragmentList
    }
    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )

    //添加一个Fragment
    fun addFragment(fragment:Fragment ){
        mFragmentList.add(fragment);
        notifyDataSetChanged();
    }
    //删除一个Fragment
    fun removeFragment(){
        if (mFragmentList.size > 0 ){
            mFragmentList.removeAt(mFragmentList.size - 1);
            notifyDataSetChanged();
        }
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList.get(position)
    }
}