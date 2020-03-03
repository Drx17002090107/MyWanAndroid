package com.example.machenike.mywanandroid.ui.main.project

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.project.ProjectInfoModel
import com.example.machenike.mywanandroid.model.project.ProjectNamesModel
import com.example.machenike.mywanandroid.ui.base.BaseVMFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_project.*


/**
created time：2019/12/26 10:09
created by：动感超人
Describe ：
 */
class ProjectFragment : BaseVMFragment<ProjectViewModel>() {
    val titleList by lazy { arrayListOf<String>() }
    val mAdapter by lazy { ProjectAdapter() }
    var currentPage = 0
    var selectTitle = "最新项目"
    lateinit var idMap :HashMap<String,Int>
    override fun getLayoutResId(): Int = R.layout.fragment_project
    override fun providerVMClass(): Class<ProjectViewModel> = ProjectViewModel::class.java

    override fun initViewData(view: View, savedInstanceState: Bundle?) {
        rvProjectArticles.layoutManager = LinearLayoutManager(context)
        initAdapter()
        initTabLayout()
        projectRefreshLayout.setOnRefreshListener {
            refresh()
        }

        refresh()

    }
    fun initTabLayout(){
        tlProjectNavigation.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                currentPage = 0
                selectTitle = p0?.text as String
                projectRefreshLayout.isRefreshing = true
                mAdapter.data.clear()
                getArticles()
            }

        })
    }
    fun initAdapter(){
        mAdapter.run {
            setOnLoadMoreListener({getArticles()},rvProjectArticles)
        }
        rvProjectArticles.adapter = mAdapter
    }
    fun refresh(){
        projectRefreshLayout.isRefreshing = true
        currentPage = 0
        mViewModel.getProjectNames()
        getArticles()
    }
    fun getArticles() {
        if(selectTitle.equals("最新项目"))
            mViewModel.getNewProjectInfo(currentPage)
        else
            mViewModel.getProjectInfoModel(currentPage,idMap.get(selectTitle)!!)
    }
    fun setArticles(data:List<ProjectInfoModel>){
        mAdapter.run {
            if(projectRefreshLayout.isRefreshing)
                replaceData(data)
            else{
                addData(data)
                loadMoreComplete()
            }
        }
        projectRefreshLayout.isRefreshing = false
        currentPage++
    }
    override fun startObserve() {
        super.startObserve()
        mViewModel.run {
            mProjectNamesModel.observe(this@ProjectFragment, Observer {
                titleList.add("最新项目")
                idMap = HashMap(mProjectNamesModel.value!!.data.size)

                for(names:ProjectNamesModel in mProjectNamesModel.value!!.data){
                    titleList.add(names.name)
                    idMap.put(names.name,names.id)
                }

                titleList.forEach{
                    tlProjectNavigation.addTab(tlProjectNavigation.newTab().setText(it))
                }
            })
            mProjectNewInfoModel.observe(this@ProjectFragment, Observer {
                setArticles(mProjectNewInfoModel.value!!.data.datas)
            })
            mProjectInfoModel.observe(this@ProjectFragment, Observer {
                setArticles(mProjectInfoModel.value!!.data.datas)
            })
        }
    }
}