package com.example.machenike.mywanandroid.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 * @description:
 * @author: litrainy
 * @create: 2019-08-28 10:48
 **/
abstract class BaseVMFragment<VM : BaseViewModel> : Fragment() {

    protected lateinit var mViewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        initViewData(view, savedInstanceState)
        startObserve()
        super.onViewCreated(view, savedInstanceState)
    }

    open fun startObserve() {
        mViewModel.mException.observe(viewLifecycleOwner, Observer { it?.let { onError(it) } })
    }

    open fun onError(e: Throwable) {}

    abstract fun getLayoutResId(): Int

    abstract fun initViewData(view: View, savedInstanceState: Bundle?)

    private fun initVM() {
        providerVMClass()?.let {
            //todo 这里如何获取viewModel有待商榷，待深入理解后在替换废弃的方法
//            // With ViewModelFactory
//            mViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(it)
//            mViewModel = ViewModelProvider.NewInstanceFactory().create(it)
//            //Without ViewModelFactory
            mViewModel = ViewModelProvider(this).get(it)
//            mViewModel = ViewModelProviders.of(this).get(it)
            lifecycle.addObserver(mViewModel)
        }

    }

    open fun providerVMClass(): Class<VM>? = null

    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }
}