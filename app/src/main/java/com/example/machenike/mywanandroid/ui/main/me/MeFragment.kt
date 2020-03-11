package com.example.machenike.mywanandroid.ui.main.me

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.coder.zzq.smartshow.toast.SmartToast
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.ui.base.BaseVMFragment
import com.example.machenike.mywanandroid.ui.login.LoginActivity
import com.example.machenike.mywanandroid.utils.Preference
import kotlinx.android.synthetic.main.fragment_me.*
import luyao.util.ktx.ext.startKtxActivity

/**
created time：2019/12/18 11:04
created by：动感超人
Describe ：
 */
class MeFragment : BaseVMFragment<MeViewModel>() {
    private val isLogin by Preference(Preference.IS_LOGIN,false)

    override fun getLayoutResId(): Int = R.layout.fragment_me
    override fun providerVMClass(): Class<MeViewModel> = MeViewModel::class.java
    override fun initViewData(view: View, savedInstanceState: Bundle?) {
        imgUserIcon.setOnClickListener{
            if(!isLogin)
            startKtxActivity<LoginActivity>()
        }
        tvUserName.setOnClickListener {
            if(!isLogin)
            startKtxActivity<LoginActivity>()
        }
    }

    override fun startObserve() {
       mViewModel.run {
           mPersonalScore.observe(this@MeFragment, Observer {
               val scoreModel = mPersonalScore.value!!
                tvUserName.text = scoreModel.username
                tvUserInfo.text = "ID:${scoreModel.userId}    排名:${scoreModel.rank}"
                tvUserLevel.text = "Lv ${scoreModel.level}"
                tvUserScore.text = scoreModel.coinCount.toString()
           })
           mErrorMessage.observe(this@MeFragment, Observer {
               SmartToast.error(mErrorMessage.value)
           })
       }
    }

    override fun onStart() {
        if(isLogin)
            mViewModel.getPersonalScoreFromRquest()
        super.onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MeFragment()
    }

}