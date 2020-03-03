package com.example.machenike.mywanandroid.ui.login

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.coder.zzq.smartshow.toast.SmartToast
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.ui.MainActivity
import com.example.machenike.mywanandroid.ui.base.BaseVMActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.head_normal.*
import luyao.util.ktx.ext.startKtxActivity


class LoginActivity : BaseVMActivity<LoginViewModel>() {


    private lateinit var userName:String
    private lateinit var passWord:String

    override fun getLayoutResId(): Int = R.layout.activity_login
    override fun providerVMClass(): Class<LoginViewModel>? = LoginViewModel::class.java

    override fun initViewData(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView: View = window.decorView
            decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            )
            window.statusBarColor = Color.TRANSPARENT
        }
        btnLogin.setOnClickListener {
            if(checkInput())
            mViewModel.login(userName,passWord)
        }
        imgHeadBack.setOnClickListener{
            finish()
        }
    }
    private fun checkInput():Boolean{
        userName = etLoginUserName.text.toString()
        passWord = etLoginPassWord.text.toString()
        if(userName.isEmpty()||passWord.isEmpty()){
            SmartToast.info("用户名或密码不能为空")
            return false
        }
        return true
    }

    override fun startObserve() {
        mViewModel.run {
            mErrorMessage.observe(this@LoginActivity, Observer {
                SmartToast.error(mViewModel.mErrorMessage.value)
            })
            mLoginUserInfo.observe(this@LoginActivity, Observer {
                SmartToast.success("登录成功")
                finish()
            })
        }


    }
}
