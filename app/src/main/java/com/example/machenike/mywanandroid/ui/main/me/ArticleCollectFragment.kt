package com.example.machenike.mywanandroid.ui.main.me

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.coder.zzq.smartshow.toast.SmartToast
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.me.ArticleCollect
import com.example.machenike.mywanandroid.model.me.Data
import com.example.machenike.mywanandroid.ui.BrowserNormalActivity
import com.example.machenike.mywanandroid.ui.base.BaseVMFragment
import com.example.machenike.mywanandroid.ui.login.state.UserContext
import kotlinx.android.synthetic.main.fragment_article_collect.*
import kotlinx.android.synthetic.main.item_no_picture_ariticle.*
import luyao.util.ktx.ext.startKtxActivity

/**
created time：2020/5/6 16:18
created by：动感超人
Describe ：
 */
class ArticleCollectFragment:BaseVMFragment<ArticleCollectViewModel>() {
    override fun getLayoutResId(): Int  = R.layout.fragment_article_collect
    override fun providerVMClass(): Class<ArticleCollectViewModel> = ArticleCollectViewModel::class.java

    val mJudgeList:MutableList<Boolean> = ArrayList()
    var currentPage = 0
    val mAdapter by lazy { ArticleCollectAdapter() }

    override fun initViewData(view: View, savedInstanceState: Bundle?) {
        rvCollectArticles.layoutManager = LinearLayoutManager(context)

        initAdapter()

        collectArticleRefreshLayout.setOnRefreshListener { refresh() }
        refresh()
    }

    private fun initAdapter(){
        mAdapter.run {
            setOnLoadMoreListener({mViewModel.getCollectArticle(currentPage)},rvCollectArticles)
            setOnItemClickListener{_,_,position->
                startKtxActivity<BrowserNormalActivity>(extra = Bundle().apply {
                    putString("title",mAdapter.data[position].title)
                    putString("url",mAdapter.data[position].link)
                })
            }
            onItemChildClickListener = this@ArticleCollectFragment.onMyItemChildClickListener

        }
        rvCollectArticles.adapter = mAdapter
    }

    private val onMyItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener{_,view,position->
        when(view.id){
            R.id.imgHomeCollect -> {
                UserContext.collect(context,{
                    //view.findViewById<ImageView>(R.id.imgHomeCollect)
                        val image = mAdapter.getViewByPosition(position,R.id.imgHomeCollect) as ImageView
                        image.setImageResource(R.drawable.ic_collect_normal_gray)
                        SmartToast.info("点击收藏")
                        mAdapter.data[position].run {
                            if(mJudgeList.get(position)==true){
                                mViewModel.cancelCollectArticle(id,originId)
                                image.setImageResource(R.drawable.ic_collect_normal_gray)
                                mJudgeList[position] = false
                            }else{
                                mViewModel.collectArticle(originId)
                                image.setImageResource(R.drawable.ic_collect_selected)
                                mJudgeList[position] = true
                            }
                        }

                })
            }
        }
    }
    private fun setArticles(articlecollect: ArticleCollect){
        mAdapter.run {
            //没有分页了 停止加载
        if(articlecollect.offset>=articlecollect.total){
            loadMoreEnd()
            return
        }
            if(collectArticleRefreshLayout.isRefreshing)
                replaceData(articlecollect.datas)
            else
                addData(articlecollect.datas)
            loadMoreComplete()
        }

        collectArticleRefreshLayout.isRefreshing = false
        currentPage++
    }
    private fun refresh(){
        collectArticleRefreshLayout.isRefreshing = true
        currentPage = 0
        mViewModel.getCollectArticle(currentPage)
    }
    override fun startObserve() {
        super.startObserve()
        mViewModel.run {
            mCollectArticles.observe(this@ArticleCollectFragment, Observer {
                for (i in 1..mCollectArticles.value!!.size){
                    mJudgeList.add(true)
                }
                setArticles(mCollectArticles.value!!)
            })
            mErrorMessage.observe(this@ArticleCollectFragment, Observer {
                SmartToast.error(mErrorMessage.value)
            })
        }
    }
    companion object{
        fun newInstance() = ArticleCollectFragment()
    }
}