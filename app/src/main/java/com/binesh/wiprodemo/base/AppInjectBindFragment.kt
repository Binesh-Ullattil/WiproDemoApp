package com.binesh.wiprodemo.base

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.AndroidSupportInjection

abstract class AppInjectBindFragment : BaseFragment() {

    private var viewBinding: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = DataBindingUtil.getBinding(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding?.unbind()
        viewBinding = null
    }

}