package com.tony.githubclient.ui.home.myrepo

import com.tony.githubclient.base.BaseFragment
import com.tony.githubclient.databinding.FragmentMyRepositoryBinding

class MyRepositoryFragment : BaseFragment<FragmentMyRepositoryBinding>() {


    override fun getDataBindingClass(): Class<FragmentMyRepositoryBinding> {
        return FragmentMyRepositoryBinding::class.java
    }
}