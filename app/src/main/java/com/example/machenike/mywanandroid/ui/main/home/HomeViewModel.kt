package com.example.machenike.mywanandroid.ui.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.home.ArticleInfoModel
import com.example.machenike.mywanandroid.model.home.ArticlePageModel
import com.example.machenike.mywanandroid.model.home.BannerModel
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2019/12/19 9:53
created by：动感超人
Describe ：
 */
class HomeViewModel: BaseViewModel(){
    val mArticlePageModel:MutableLiveData<ArticlePageModel> = MutableLiveData()
    val mArticleInfoModel:MutableLiveData<List<ArticleInfoModel>> = MutableLiveData()
    val mBannerModel:MutableLiveData<List<BannerModel>> = MutableLiveData()
    val mErrorManager:MutableLiveData<String> = MutableLiveData()

    fun getBanner(){
        launch(){
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getBannerData()}
            executeResponse(
                response = result,
                successBlock = {
                    mBannerModel.value = result.body()?.data
                },errorBlock = {
                    mErrorManager.value = executeMessage(result.errorBody())
                }
            )
        }
    }
    fun getArticles(page:Int){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getHomeArticlePage(page)}
            executeResponse(
                response = result,
                successBlock = {
                    mArticlePageModel.value = result.body()?.data
                },
                errorBlock = {
                    mErrorManager.value = executeMessage(result.errorBody())
                }
            )

        }
    }
    fun getTopArticles(){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getTopArticles()}
            executeResponse(
                response = result,
                successBlock = {
                    mArticleInfoModel.value = result.body()?.data
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
                else NetWorkClient.retrofitService.cancelCollectArticle(articleId)
            }
        }
    }

}