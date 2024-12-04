package com.pulsedonor.app.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    lateinit var binding: VB

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    abstract fun inflateBinding(layoutInflater: LayoutInflater): VB


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflateBinding(inflater)
//        requireActivity().window.getDecorView()
//            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        onClicks()
    }

    abstract fun observeViewModel()
    abstract fun initViews()
    abstract fun onClicks()

}