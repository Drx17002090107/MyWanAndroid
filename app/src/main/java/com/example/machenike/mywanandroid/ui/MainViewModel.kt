package com.example.machenike.mywanandroid.ui

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.me.PersonalScore
import com.example.machenike.mywanandroid.model.weather.NowModel
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import com.example.machenike.mywanandroid.utils.LocationUtilsJava
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2020/3/31 14:28
created by：动感超人
Describe ：
 */
class MainViewModel : BaseViewModel() {
    var mPersonalScore: MutableLiveData<PersonalScore> = MutableLiveData()
    val mNowData:MutableLiveData<NowModel> = MutableLiveData()
    var mErrorMessage: MutableLiveData<String> = MutableLiveData()
    fun getPersonalScoreFromRquest(){
        launch {
            val result = withContext(Dispatchers.IO){ NetWorkClient.retrofitService.getPersonalScore()}
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

    fun getLocation(context: Context?){
        LocationUtilsJava.register(context, 0, 1000000, object :
            LocationUtilsJava.OnLocationChangeListener {
            override fun getLastKnownLocation(location: Location) {
                Log.e("drx","onLocationChanged: ${location.latitude}")
//                getForecastFree(location.longitude,location.latitude)
                Log.d("111111111111","111111111111")
                getNowFree(location.longitude,location.latitude)
            }
            override fun onLocationChanged(location: Location) {
                //位置发生变化
            }
            override fun onStatusChanged(
                provider: String,
                status: Int,
                extras: Bundle
            ) {
                //定位发生变化 开启或关闭
            }
        })
    }


    fun getNowFree(longitude:Double?,latitude:Double?){

        launch {
            val result = withContext(Dispatchers.IO){
                val location = "${longitude},${latitude}"
                NetWorkClient.retrofitWeatherService.getNowFree(location)
            }
            executeResponse(response = result,
                successBlock = {
                    mNowData.value = result.body()
                },errorBlock = {
                    mErrorMessage.value = executeMessage(result.errorBody())
                }

            )
        }
    }
}