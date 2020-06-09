package com.example.machenike.mywanandroid.ui.main.me

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.me.ArticleCollect
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2020/5/6 16:19
created by：动感超人
Describe ：
 */
class ArticleCollectViewModel: BaseViewModel() {
    var mCollectArticles: MutableLiveData<ArticleCollect> = MutableLiveData()
    var mErrorMessage: MutableLiveData<String> = MutableLiveData()
    fun getCollectArticle(page:Int){
        launch {
            val result = withContext(Dispatchers.IO){ NetWorkClient.retrofitService.getCollectArticles(page)}
            executeResponse(
                response = result,
                successBlock = {
                    mCollectArticles.value = result.body()?.data
                },
                errorBlock = {
                    mErrorMessage.value = executeMessage(result.errorBody())
                }
            )
        }
    }

    fun cancelCollectArticle(id:Int,orginId:Int){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.cancelCollectArticle(id,orginId)}
            executeResponse(
                response = result,
                successBlock = {},
                errorBlock = {mErrorMessage.value = executeMessage(result.errorBody())}
            )
        }

    }

    fun collectArticle(articleId:Int){
        Log.d("drx","收藏"+articleId)
        launch {
            withContext(Dispatchers.IO){
                NetWorkClient.retrofitService.collectArticle(articleId)
            }
        }
    }
}