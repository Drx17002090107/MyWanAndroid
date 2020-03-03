package com.example.machenike.mywanandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.machenike.mywanandroid.R
import com.example.machenike.mywanandroid.ui.main.home.HomeFragment
import com.example.machenike.mywanandroid.ui.main.me.MeFragment
import com.example.machenike.mywanandroid.ui.main.project.ProjectFragment
import com.example.machenike.mywanandroid.ui.main.wechart.WeChartFragment
import com.example.machenike.mywanandroid.utils.Preference


class MainActivity : AppCompatActivity() {
    val user_json by Preference(Preference.USER_GSON,"登录")
    val isLogin by Preference(Preference.IS_LOGIN,false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.frameLayoutMainContent,
            WeChartFragment()
        ).commit()
    }

//    private fun switchFragment(targetFragment: androidx.fragment.app.Fragment) {
//        val transition = supportFragmentManager.beginTransaction()
//        if (!targetFragment.isAdded) {
//            if (currentFragment != null) transition.hide(currentFragment!!)
//            transition.add(R.id.content, targetFragment, targetFragment.javaClass.name)
//        } else {
//            transition.hide(currentFragment!!).show(targetFragment)
//        }
//        transition.commit()
//        currentFragment = targetFragment
//    }
}
