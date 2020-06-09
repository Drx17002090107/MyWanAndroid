package com.example.machenike.mywanandroid.ui.main.project

import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.project.ProjectNamesModel
import com.example.machenike.mywanandroid.model.project.ProjectPageModel
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.net.response.WanResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2019/12/26 10:13
created by：动感超人
Describe ：
 */
class ProjectViewModel: BaseViewModel() {
    val mProjectNamesModel:MutableLiveData<WanResponse<List<ProjectNamesModel>>> = MutableLiveData()
    val mProjectInfoModel:MutableLiveData<WanResponse<ProjectPageModel>> = MutableLiveData()
    val mProjectNewInfoModel:MutableLiveData<WanResponse<ProjectPageModel>> = MutableLiveData()
    val mErrorMessage:MutableLiveData<String> = MutableLiveData()
    fun getNewProjectInfo(page:Int){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getNewProjectInfo(page)}
            executeResponse(
                response = result,
                successBlock = {mProjectNewInfoModel.value = result.body()!!},
                errorBlock = { mErrorMessage.value = executeMessage(result.errorBody())})
        }
    }
    fun getProjectNames(){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getProjectNames()}
            executeResponse(
                response = result,
                successBlock = {mProjectNamesModel.value = result.body()!!},
                errorBlock = { mErrorMessage.value = executeMessage(result.errorBody())})
        }
    }
    fun getProjectInfoModel(page:Int,id:Int){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getProjectInfo(page,id)}
            executeResponse(
                response = result,
                successBlock = {mProjectInfoModel.value = result.body()!!},
                errorBlock = { mErrorMessage.value = executeMessage(result.errorBody())})
        }
    }

    fun collectArticle(articleId:Int,isCollect:Boolean){
        launch {
            withContext(Dispatchers.IO){
                if(isCollect)NetWorkClient.retrofitService.collectArticle(articleId)
                else NetWorkClient.retrofitService.cancelOriginCollectArticle(articleId)
            }
        }
    }
}