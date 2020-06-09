package com.example.machenike.mywanandroid.ui.main.me

import androidx.lifecycle.MutableLiveData
import com.example.machenike.mywanandroid.model.me.ArticleCollect
import com.example.machenike.mywanandroid.net.NetWorkClient
import com.example.machenike.mywanandroid.net.executeMessage
import com.example.machenike.mywanandroid.net.executeResponse
import com.example.machenike.mywanandroid.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
created time：2020/5/6 10:33
created by：动感超人
Describe ：
 */
class CollectViewModel: BaseViewModel() {
    var mErrorMessage:MutableLiveData<String> = MutableLiveData()
}
