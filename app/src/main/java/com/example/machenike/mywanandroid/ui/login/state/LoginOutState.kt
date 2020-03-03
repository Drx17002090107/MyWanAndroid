package com.example.machenike.mywanandroid.ui.login.state

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.coder.zzq.smartshow.toast.SmartToast
import com.example.machenike.mywanandroid.ui.login.LoginActivity

/**
created time：2020/1/3 10:35
created by：动感超人
Describe ：
 */
class LoginOutState : UserState {
    override fun collect(context: Context?, block: () -> Unit) {
        goLoginActivity(context)
    }

    override fun login(context: Context?) {
        goLoginActivity(context)
    }
    private fun goLoginActivity(context:Context?){
        context?.run {
            SmartToast.info("请先登录")
            startActivity(Intent(context,LoginActivity::class.java))
        }

    }
}