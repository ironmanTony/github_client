package com.tony.githubclient.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.tony.githubclient.databinding.FragmentHomeBinding

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    abstract fun getDataBindingClass(): Class<T>

    protected lateinit var mBinding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val paramsClass = arrayOf<Class<*>>(
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        val method = getDataBindingClass().getDeclaredMethod("inflate", *paramsClass)
        mBinding = method.invoke(null, inflater, container, false) as T
        return mBinding.root
    }
}