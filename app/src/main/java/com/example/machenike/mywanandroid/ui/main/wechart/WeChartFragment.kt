package com.example.machenike.mywanandroid.ui.main.wechart

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.coder.zzq.smartshow.toast.SmartToast
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.wechart.WeChartInfo
import com.example.machenike.mywanandroid.model.wechart.WeChartNames
import com.example.machenike.mywanandroid.ui.BrowserNormalActivity
import com.example.machenike.mywanandroid.ui.base.BaseVMFragment
import com.example.machenike.mywanandroid.utils.ChartUtils
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_wechart.*
import luyao.util.ktx.ext.startKtxActivity

/**
created time：2020/1/2 16:54
created by：动感超人
Describe ：
 */
class WeChartFragment: BaseVMFragment<WeChartViewModel>() {
    private val nameList by lazy { arrayListOf<String>() }
    private val mAdapter by lazy { WeChartAdapter() }
    private lateinit var selectedName:String
    private var currentPage = 0
    private val idMap:HashMap<String,Int> by lazy { HashMap<String,Int>() }

    override fun getLayoutResId(): Int = R.layout.fragment_wechart

    override fun providerVMClass(): Class<WeChartViewModel> = WeChartViewModel::class.java

    override fun initViewData(view: View, savedInstanceState: Bundle?) {
        rvWeChartArticles.layoutManager = LinearLayoutManager(context)
        initTabLayout()
        initAdapter()
        refresh()
        weChartRefreshLayout.setOnRefreshListener { refresh() }
    }

    override fun startObserve() {
        super.startObserve()
        mViewModel.run {
            mWeChartNames.observe(this@WeChartFragment, Observer {
                for (name:WeChartNames in mWeChartNames.value!!){
                    nameList.add(name.name)
                    idMap.put(name.name,name.id)
                }
                selectedName = nameList.get(0)

                nameList.forEach{
                    tlWeChartNavigation.addTab(tlWeChartNavigation.newTab().setText(it))
                }
                getArticles()
            })
            mWeChartPage.observe(this@WeChartFragment, Observer {
                setArticles(mWeChartPage.value!!.datas)
            })
            mErrorMessage.observe(this@WeChartFragment, Observer {
                SmartToast.error(mErrorMessage.value)
            })
        }
    }
    fun refresh(){
        weChartRefreshLayout.isRefreshing = true
        currentPage = 0
        mViewModel.getWeChartNames()
    }
    fun initAdapter(){
        mAdapter.run {
            setOnLoadMoreListener({getArticles()},rvWeChartArticles)
            setOnItemClickListener{_,_,position->
                startKtxActivity<BrowserNormalActivity>(extra = Bundle().apply {
                    putString("title",ChartUtils.replaceQuotationMarks(mAdapter.data[position].title))
                    putString("url",mAdapter.data[position].link)
                })
            }
        }
        rvWeChartArticles.adapter = mAdapter
    }
    fun initTabLayout(){
        tlWeChartNavigation.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                currentPage = 0
                selectedName = p0?.text as String
                weChartRefreshLayout.isRefreshing = true
                mAdapter.data.clear()
                getArticles()
            }

        })
    }
    fun getArticles(){
        mViewModel.getWeChartPage(idMap.get(selectedName)!!,currentPage)
    }
    fun setArticles(data:List<WeChartInfo>){
        mAdapter.run {
            if(weChartRefreshLayout.isRefreshing){
                setNewData(data)
                weChartRefreshLayout.isRefreshing = false
            } else{
                addData(data)
                loadMoreComplete()
            }
        }
        currentPage++
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeChartFragment()
    }
}