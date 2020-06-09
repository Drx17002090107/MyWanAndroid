package com.example.machenike.mywanandroid.ui.main.wechart

import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.wechart.WeChartNames
import com.example.machenike.mywanandroid.model.wechart.WeChartPage
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
created time：2020/1/2 16:54
created by：动感超人
Describe ：
 */
class WeChartViewModel : BaseViewModel() {
    val mWeChartPage:MutableLiveData<WeChartPage> = MutableLiveData()
    val mWeChartNames:MutableLiveData<List<WeChartNames>> = MutableLiveData()
    val mErrorMessage:MutableLiveData<String> = MutableLiveData()
    fun getWeChartNames(){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getWeChartNames()}
            executeResponse(
                response = result,
                successBlock = {
                    mWeChartNames.value = result.body()!!.data
                },errorBlock = {
                    mErrorMessage.value = executeMessage(result.errorBody())
                }
            )
        }
    }
    fun getWeChartPage(id:Int,page:Int){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getWeChartInfo(id, page)}
            executeResponse(
                response = result,
                successBlock = {
                    mWeChartPage.value = result.body()!!.data
                },errorBlock = {
                    mErrorMessage.value = executeMessage(result.errorBody())
                }
            )
        }
    }
    fun findWeChartInfo(id:Int,page:Int,keyWord:String){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.findWeChartInfo(id, page, keyWord)}
            executeResponse(
                response = result,
                successBlock = {
                    mWeChartPage.value = result.body()!!.data
                },errorBlock = {
                    mErrorMessage.value = executeMessage(result.errorBody())
                }
            )
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