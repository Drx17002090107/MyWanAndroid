package com.example.machenike.mywanandroid.ui.main.navigation

import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.navigation.NavigationModel
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2020/3/24 11:06
created by：动感超人
Describe ：
 */
class NavigationViewModel: BaseViewModel() {
    val mNavigationInfo:MutableLiveData<List<NavigationModel>> = MutableLiveData()
    val mErrorManager:MutableLiveData<String> = MutableLiveData()

    fun getNavigationInfo(){
        launch {
            val result = withContext(Dispatchers.IO){
                NetWorkClient.retrofitService.getNavigationInfo()
            }
            executeResponse(
                response = result,
                successBlock ={mNavigationInfo.value = result.body()!!.data} ,
                errorBlock = { mErrorManager.value = executeMessage(result.errorBody())}
            )
        }
    }
}