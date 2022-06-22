package com.tony.githubclient.ui.account

import android.os.Bundle
import android.view.View
import com.tony.githubclient.base.BaseFragment
import com.tony.githubclient.databinding.FragmentAccountBinding

class AccountFragment : BaseFragment<FragmentAccountBinding>() {

    companion object {
        const val TAG = "AccountFragment"
    }

    override fun getDataBindingClass(): Class<FragmentAccountBinding> {
        return FragmentAccountBinding::class.java
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}