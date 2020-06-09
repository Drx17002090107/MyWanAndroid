package com.example.machenike.mywanandroid.ui.main.me

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.me.PersonalScore
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2019/12/18 11:06
created by：动感超人
Describe ：
 */
class MeViewModel : BaseViewModel(){
    var mPersonalScore:MutableLiveData<PersonalScore> = MutableLiveData()
    var mErrorMessage:MutableLiveData<String> = MutableLiveData()
    fun getPersonalScoreFromRquest(){
        launch {
            val result = withContext(Dispatchers.IO){NetWorkClient.retrofitService.getPersonalScore()}
            executeResponse(
                response = result,
                successBlock = {
                    mPersonalScore.value = result.body()?.data
                    Log.d("_____________",result.body()?.data.toString())
                },
                errorBlock = {
                    mErrorMessage.value= executeMessage(result.errorBody())
                })
        }
    }
}