package com.pulsedonor.app.base.activity

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity(), HasActivityInjector,
    HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var binding: Binding

    override fun activityInjector() = activityInjector
    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector


    abstract fun inflateBinding(layoutInflater: LayoutInflater): Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = inflateBinding(layoutInflater)
        setContentView(binding.root)
        initViews()
        onClicks()
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()

    }

    abstract fun initViews()
    abstract fun observeViewModel()
    abstract fun onClicks()


}