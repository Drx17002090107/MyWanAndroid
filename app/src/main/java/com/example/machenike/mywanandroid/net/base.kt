package com.example.machenike.mywanandroid.net

import android.content.Context
import android.net.ConnectivityManager
import com.example.machenike.mywanandroid.net.response.WanResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

/**
created time：2019/12/12 16:55
created by：动感超人
Describe ：
 */

const val BASE_URL = "https://www.wanandroid.com"

fun executeMessage(errorBody: ResponseBody?): String {
    return errorBody?.string()?.let {
        JSONObject(it).getString("errorMsg")
    } ?: "未知错误"
}

suspend fun <T> executeResponse(response: Response<T>, successBlock: suspend CoroutineScope.() -> Unit, errorBlock: suspend CoroutineScope.() -> Unit) {
    coroutineScope {
        if (response.isSuccessful)
            successBlock()
        else
            errorBlock()
    }
}
fun isNetworkAvailable(context: Context): Boolean {
    val manager = context.applicationContext.getSystemService(
        Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = manager.activeNetworkInfo
    return !(null == info || !info.isAvailable)
}