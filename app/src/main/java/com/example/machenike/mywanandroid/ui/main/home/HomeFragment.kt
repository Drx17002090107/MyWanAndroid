package com.example.machenike.mywanandroid.ui.main.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.coder.zzq.smartshow.toast.SmartToast
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.home.ArticleInfoModel
import com.example.machenike.mywanandroid.model.home.BannerModel
import com.example.machenike.mywanandroid.model.home.Data
import com.example.machenike.mywanandroid.ui.BrowserNormalActivity
import com.example.machenike.mywanandroid.ui.base.BaseVMFragment
import com.example.machenike.mywanandroid.ui.login.LoginActivity
import com.example.machenike.mywanandroid.utils.GlideImageLoader
import com.example.machenike.mywanandroid.utils.Preference
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import luyao.util.ktx.ext.dp2px
import luyao.util.ktx.ext.startKtxActivity


/**
created time：2019/12/19 9:35
created by：动感超人
Describe ：
 */
class HomeFragment: BaseVMFragment<HomeViewModel>() {
    val mAdapter by lazy { ArticleAdapter() }
    val isLogin by Preference(Preference.IS_LOGIN,false)
    var currentPage = 0
    override fun getLayoutResId(): Int = R.layout.fragment_home
    override fun providerVMClass(): Class<HomeViewModel> = HomeViewModel::class.java

    private val bannerImages = mutableListOf<String>()
    private val bannerTitles = mutableListOf<String>()
    private val bannerUrls = mutableListOf<String>()
    private val banner by lazy { com.youth.banner.Banner(context) }

    override fun initViewData(view: View, savedInstanceState: Bundle?) {
        rvHomeShowArticles.layoutManager = LinearLayoutManager(context)

        initBanner()
        initAdapter()

        homeRefreshLayout.setOnRefreshListener {
            refresh()
        }
        refresh()
    }

    private fun refresh() {
        homeRefreshLayout.isRefreshing = true
        currentPage = 0
        mViewModel.getBanner()
        mViewModel.getTopArticles()
//        mViewModel.getArticles(currentPage)
    }

    fun initAdapter(){
        mAdapter.run {
            setOnLoadMoreListener({mViewModel.getArticles(currentPage)},rvHomeShowArticles)
            addHeaderView(banner)
            setOnItemClickListener{_,_,position->
                startKtxActivity<BrowserNormalActivity>(extra = Bundle().apply {
                    putString("title",mAdapter.data[position].title)
                    putString("url",mAdapter.data[position].link)
                })
            }
            onItemChildClickListener=this@HomeFragment.onItemChildClickListener
        }
        rvHomeShowArticles.adapter = mAdapter
    }
    private val onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
        when (view.id) {
            R.id.imgHomeCollect -> {
                if (isLogin) {
                    mAdapter.run {
                        data[position].run {
                            collect = !collect
                            mViewModel.collectArticle(id, collect)
                        }
                        notifyDataSetChanged()
                    }
                } else {
                    activity?.startKtxActivity<LoginActivity>()
                }
            }
        }
    }
    fun initBanner(){
        banner.run {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,banner.dp2px(200f))
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            setImageLoader(GlideImageLoader())
            setOnBannerListener(){
                startKtxActivity<BrowserNormalActivity>(extra = Bundle().apply {
                    putString("title",mAdapter.data[it].title)
                    putString("url",mAdapter.data[it].link)
                })
            }
        }
    }
    override fun startObserve() {
        mViewModel.run {
            mArticleInfoModel.observe(this@HomeFragment, Observer {
                setArticles(mArticleInfoModel.value!!)
            })
            mArticlePageModel.observe(this@HomeFragment, Observer {
                setArticles(mArticlePageModel.value!!.datas)
            })
            mErrorManager.observe(this@HomeFragment, Observer {
                SmartToast.error(mErrorManager.value)
            })
            mBannerModel.observe(this@HomeFragment, Observer {
                setBanner(mBannerModel.value!!)
            })
        }
    }


    fun setArticles(data:List<ArticleInfoModel>){
        mAdapter.run {
            if(homeRefreshLayout.isRefreshing)
                replaceData(data)
            else
            addData(data)
            loadMoreComplete()
        }

        homeRefreshLayout.isRefreshing = false
        currentPage++
    }
    fun setBanner(data:List<BannerModel>){
        for (banner in data){
            bannerImages.add(banner.imagePath)
            bannerTitles.add(banner.title)
            bannerUrls.add(banner.url)
        }
        banner.setImages(bannerImages)
            .setBannerTitles(bannerTitles)
            .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            .setDelayTime(3000)
        banner.start()
    }
    companion object {
       fun newInstance() = HomeFragment()
    }
}