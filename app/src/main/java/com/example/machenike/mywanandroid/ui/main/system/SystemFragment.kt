package com.example.machenike.mywanandroid.ui.main.system

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.ui.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_system.*

/**
created time：2020/3/17 21:00
created by：动感超人
Describe ：参考
RecyClerView获取Item位置 https://blog.csdn.net/suyimin2010/article/details/84502075
 */
class SystemFragment : BaseVMFragment<SystemViewModel>() {
    override fun getLayoutResId(): Int = R.layout.fragment_system
    override fun providerVMClass(): Class<SystemViewModel> = SystemViewModel::class.java

    override fun initViewData(view: View, savedInstanceState: Bundle?) {

        rvSystemContent.layoutManager = LinearLayoutManager(context)
        mViewModel.getSystemTag()
        //tlRecordNavigation.setupWithViewPager(vpRecordContent)
    }

    override fun startObserve() {
        super.startObserve()
        mViewModel.run {
            mSystemModel.observe(this@SystemFragment, Observer {
//                val mAdapterJava = SystemAdapterJava(mSystemModel.value,context)
//                rvSystemContent.adapter = mAdapterJava
                val mAdapter = SystemAdapter()
                mAdapter.addData(mSystemModel.value!!)
                rvSystemContent.adapter = mAdapter
            })
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = SystemFragment()
    }


}