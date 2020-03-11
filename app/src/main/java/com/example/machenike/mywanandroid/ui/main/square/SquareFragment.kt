package com.example.machenike.mywanandroid.ui.main.square

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.coder.zzq.smartshow.toast.SmartToast
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.square.SquareInfo
import com.example.machenike.mywanandroid.ui.BrowserNormalActivity
import com.example.machenike.mywanandroid.ui.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_square.*

import luyao.util.ktx.ext.startKtxActivity

/**
created time：2020/3/5 10:48
created by：动感超人
Describe ：
 */
class SquareFragment: BaseVMFragment<SquareViewModel>() {
    override fun getLayoutResId(): Int = R.layout.fragment_square
    override fun providerVMClass(): Class<SquareViewModel> = SquareViewModel::class.java

    private var currentPage = 0
    private val mAdapter by lazy { SquareAdapter() }
    private val mTitle = arrayListOf("广场","体系","导航")

    override fun initViewData(view: View, savedInstanceState: Bundle?) {
        setTabLayout()

        rvSquareArticles.layoutManager = LinearLayoutManager(context)

        initAdapter()
        squareRefreshLayout.setOnRefreshListener {
            refresh()
        }
        refresh()

    }
    private fun setTabLayout(){
        mTitle.forEach{
            tlSquareNavigation.addTab(tlSquareNavigation.newTab().setText(it))
        }
    }
    override fun startObserve() {
        mViewModel.run {
            mSquarePage.observe(this@SquareFragment, Observer {
                setSquare(mSquarePage.value!!.datas)
            })
            mErrorManager.observe(this@SquareFragment, Observer {
                SmartToast.error(mErrorManager.value)
            })
        }
    }

    private fun setSquare(data:List<SquareInfo>){
        mAdapter.run {
            if(squareRefreshLayout.isRefreshing)
                replaceData(data)
            else
                addData(data)
            loadMoreComplete()
        }
        squareRefreshLayout.isRefreshing = false
        currentPage++
    }
    private fun initAdapter(){
        mAdapter.run {
            setOnLoadMoreListener({mViewModel.getSquareInfo(currentPage)},rvSquareArticles)
            setOnItemClickListener{_,_,position->
                startKtxActivity<BrowserNormalActivity>(extra = Bundle().apply {
                    putString("title",mAdapter.data[position].title)
                    putString("url",mAdapter.data[position].link)
                })
            }
//            onItemChildClickListener = this@SquareFragment.onItemChildClickListener
        }
        rvSquareArticles.adapter = mAdapter
    }
//    private val onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener{_,view,position->
//        when(view.id){
//            R.id.imgHomeCollect->{
//                if(isLogin){
//                    mAdapter.run {
//                        data[position].run {
//                            collect = !collect
//                        }
//                    }
//                }else{
//                    activity?.startKtxActivity<LoginActivity>()
//                }
//            }
//        }
//    }
    private fun refresh(){
        squareRefreshLayout.isRefreshing = true
        currentPage = 0
        mViewModel.getSquareInfo(currentPage)
    }
    companion object {
        @JvmStatic
        fun newInstance() = SquareFragment()
    }
}