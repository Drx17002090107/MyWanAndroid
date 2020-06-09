package com.example.machenike.mywanandroid.ui.main.navigation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.ui.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_navigation.*

/**
created time：2020/3/24 10:51
created by：动感超人
Describe ：
 */
class NavigationFragment : BaseVMFragment<NavigationViewModel>() {
    override fun getLayoutResId(): Int = R.layout.fragment_navigation
    override fun providerVMClass(): Class<NavigationViewModel> = NavigationViewModel::class.java

    override fun initViewData(view: View, savedInstanceState: Bundle?) {
        rvNavigationContent.layoutManager = LinearLayoutManager(context)
        mViewModel.getNavigationInfo()
    }

    override fun startObserve() {
        super.startObserve()
        mViewModel.run {
            mNavigationInfo.observe(this@NavigationFragment, Observer {
                val adapter = NavigationAdapter()
                adapter.addData(mNavigationInfo.value!!)
                rvNavigationContent.adapter = adapter
            })
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = NavigationFragment()
    }

}