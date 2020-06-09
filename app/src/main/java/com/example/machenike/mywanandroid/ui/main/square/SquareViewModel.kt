package com.example.machenike.mywanandroid.ui.main.square

import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.square.SquarePage
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2020/3/5 10:38
created by：动感超人
Describe ：
 */
class SquareViewModel: BaseViewModel() {
    val mErrorManager:MutableLiveData<String> = MutableLiveData()
    val mSquarePage:MutableLiveData<SquarePage> = MutableLiveData()
    fun getSquareInfo(page:Int){
        launch(){
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getSquareInfo(page)}
            executeResponse(
                response = result,
                successBlock = {
                    mSquarePage.value = result.body()?.data
                },errorBlock = {
                    mErrorManager.value = executeMessage(result.errorBody())
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