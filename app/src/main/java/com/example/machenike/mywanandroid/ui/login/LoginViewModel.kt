package com.example.machenike.mywanandroid.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.net.response.login.UserModel
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import com.example.machenike.mywanandroid.utils.Preference
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2019/12/13 17:23
created by：动感超人
Describe ：
 */
class LoginViewModel: BaseViewModel() {

    var mLoginUserInfo:MutableLiveData<UserModel> = MutableLiveData()
    var mErrorMessage:MutableLiveData<String> = MutableLiveData()
    private var isLogin by Preference(Preference.IS_LOGIN,false)
    private var userGson by Preference(Preference.USER_GSON,"")

    fun login(name:String,password:String){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.login(name,password)}
            executeResponse(
                response = result,
                successBlock = {
                    if(result.body()?.errorCode!=0){
                        mErrorMessage.value = result.body()?.errorMsg
                    }else{
                        mLoginUserInfo.value = result.body()?.data
                        isLogin = true
                        userGson = Gson().toJson(result.body()?.data)
                    }
                },
                errorBlock = {
                    mErrorMessage.value = result.body()?.errorMsg
                }
            )
        }
    }
}