package com.example.machenike.mywanandroid.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import com.coder.zzq.smartshow.toast.SmartToast
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.model.weather.HeWeather6
import com.example.machenike.mywanandroid.model.weather.NowModel
import com.example.machenike.mywanandroid.ui.base.BaseVMActivity
import com.example.machenike.mywanandroid.ui.login.LoginActivity
import com.example.machenike.mywanandroid.ui.login.state.UserContext
import com.example.machenike.mywanandroid.ui.main.home.HomeFragment
import com.example.machenike.mywanandroid.ui.main.me.CollectActivity
import com.example.machenike.mywanandroid.ui.main.me.MeFragment
import com.example.machenike.mywanandroid.ui.main.navigation.NavigationFragment
import com.example.machenike.mywanandroid.ui.main.project.ProjectFragment
import com.example.machenike.mywanandroid.ui.main.square.SquareFragment
import com.example.machenike.mywanandroid.ui.main.system.SystemFragment
import com.example.machenike.mywanandroid.ui.main.systemNavigation.SystemNavigationFragment
import com.example.machenike.mywanandroid.ui.main.wechart.WeChartFragment
import com.example.machenike.mywanandroid.utils.MyUtils.setStatusBarColor
import com.example.machenike.mywanandroid.utils.PermissionUtilsJava
import com.example.machenike.mywanandroid.utils.Preference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_me.*
import luyao.util.ktx.ext.startKtxActivity

/**
 *1.闪屏页 考虑用全屏显示活动
 *2.状态栏颜色更改一致 KO!
 * https://blog.csdn.net/songmingzhan/article/details/84452610 后台切回来 fragment又加载一遍fragment
 */

class MainActivity : BaseVMActivity<MainViewModel>() {
    private var selectItem = 0
    private val fragmentList = arrayOf(HomeFragment.newInstance(),ProjectFragment.newInstance(),
        SystemNavigationFragment.newInstance(),WeChartFragment.newInstance(),SquareFragment.newInstance())
    private val grantResults = arrayOf(PackageManager.PERMISSION_GRANTED,PackageManager.PERMISSION_GRANTED)

    private val REQUEST_PERMISSION_CODE = 1
    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun getLayoutResId(): Int = R.layout.activity_main
    override fun providerVMClass(): Class<MainViewModel> =MainViewModel::class.java

    override fun initViewData(savedInstanceState: Bundle?) {
        setStatusBarColor(this,R.color.blue)

        initLoginState()
        //判断是为了 应用切回后台后 再add一个new HomeFragment
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().add(R.id.frameLayoutMainContent,
                fragmentList[0]
            ).commit()
        }


        mainBottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home ->switchFragment(0)
                R.id.menu_project ->switchFragment(1)
                R.id.menu_square ->switchFragment(2)
                R.id.menu_wechart ->switchFragment(3)
                R.id.menu_me ->switchFragment(4)
            }
            true
        }

        ivMainMe.setOnClickListener {
            mDrawerLayout.openDrawer(GravityCompat.START)
        }

        lineLayoutMeCollect.setOnClickListener {
            startKtxActivity<CollectActivity>()
        }

        checkPermission()

    }
    fun initLoginState(){
        UserContext.mState.setPersonalScore(this,{mViewModel.getPersonalScoreFromRquest()})
        imgUserIcon.setOnClickListener{
            UserContext.login(this)
        }
        tvUserName.setOnClickListener {
            UserContext.login(this)
        }
    }
    override fun startObserve() {
        super.startObserve()
        mViewModel.run {
            mPersonalScore.observe(this@MainActivity, Observer {
                val scoreModel = mPersonalScore.value!!
                tvUserName.text = scoreModel.username
                tvUserInfo.text = "ID:${scoreModel.userId}    排名:${scoreModel.rank}"
                tvUserLevel.text = "Lv ${scoreModel.level}"
                tvUserScore.text = scoreModel.coinCount.toString()
            })
            mNowData.observe(this@MainActivity, Observer {
                val nowModel:NowModel = mNowData.value!!
                tvTemp.text = nowModel.HeWeather6[0].now.tmp
                tvLocation.text = nowModel.HeWeather6[0].basic.location
                tvWeather.text = nowModel.HeWeather6[0].now.cond_txt
            })
            mErrorMessage.observe(this@MainActivity, Observer {
                SmartToast.error(mErrorMessage.value)
            })
        }
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("111","Drx")
        UserContext.setPersonalScore(this,{mViewModel.getPersonalScoreFromRquest()})
    }



    override fun onStop() {
        super.onStop()
    }
    /**
     * 动态添加Fragment
     */
    private fun switchFragment(currentId:Int){
        supportFragmentManager.transaction {
            if(selectItem == currentId) return
            hide(fragmentList[selectItem])
            if((fragmentList[currentId]as Fragment).isAdded){
                show(fragmentList[currentId])
            }else{
                add(R.id.frameLayoutMainContent,fragmentList[currentId])
            }
            selectItem = currentId
        }
    }

    private fun checkPermission(){
        if(!PermissionUtilsJava.hasPermissions(this,permissions)){
            PermissionUtilsJava.requestPermissions(this,REQUEST_PERMISSION_CODE,permissions)
        }

        PermissionUtilsJava.onRequestPermissionsResult(REQUEST_PERMISSION_CODE,permissions,grantResults,
            object : PermissionUtilsJava.PermissionCallbacks{
                override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
                    SmartToast.error("不同意权限，将无法获取天气信息！")
                }

                override fun onPermissionsAllGranted(
                    requestCode: Int,
                    perms: MutableList<String>?,
                    isAllGranted: Boolean
                ) {
                    mViewModel.getLocation(this@MainActivity)
                }

            })

    }



}
