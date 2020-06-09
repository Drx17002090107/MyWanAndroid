package com.example.machenike.mywanandroid.ui.main.system

import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.system.SystemModel
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2020/3/19 19:38
created by：动感超人
Describe ：
 */
class SystemViewModel : BaseViewModel() {
    val mSystemModel:MutableLiveData<List<SystemModel>> = MutableLiveData()
    val mErrorManager:MutableLiveData<String> = MutableLiveData()

    fun getSystemTag(){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getSystemTag()}
            executeResponse(
                response = result,
                successBlock = {
                    mSystemModel.value = result.body()?.data
                },errorBlock = {
                    mErrorManager.value = executeMessage(result.errorBody())
                }
            )
        }
    }
}