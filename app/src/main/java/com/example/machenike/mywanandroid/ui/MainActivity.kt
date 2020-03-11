package com.example.machenike.mywanandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.ui.main.home.HomeFragment
import com.example.machenike.mywanandroid.ui.main.me.MeFragment
import com.example.machenike.mywanandroid.ui.main.project.ProjectFragment
import com.example.machenike.mywanandroid.ui.main.square.SquareFragment
import com.example.machenike.mywanandroid.ui.main.wechart.WeChartFragment
import com.example.machenike.mywanandroid.utils.Preference
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 */

class MainActivity : AppCompatActivity() {
    private val user_json by Preference(Preference.USER_GSON,"登录")
    private val isLogin by Preference(Preference.IS_LOGIN,false)
    private var selectItem = 0
    private val fragmentList = arrayOf(HomeFragment.newInstance(),ProjectFragment.newInstance(),
        SquareFragment.newInstance(),WeChartFragment.newInstance(),MeFragment.newInstance())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.frameLayoutMainContent,
            fragmentList[0]
        ).commit()

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
}
