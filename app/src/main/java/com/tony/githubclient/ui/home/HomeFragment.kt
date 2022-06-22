package com.tony.githubclient.ui.home

import android.os.Bundle
import android.view.View
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.tony.githubclient.base.BaseFragment
import com.tony.githubclient.databinding.FragmentHomeBinding
import com.tony.githubclient.ui.home.myrepo.MyRepositoryFragment
import com.tony.githubclient.ui.home.trending.TrendingFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        const val TAG = "HomeFragment"
    }

    override fun getDataBindingClass(): Class<FragmentHomeBinding> {
        return FragmentHomeBinding::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val adapter = FragmentPagerItemAdapter(
            childFragmentManager,
            FragmentPagerItems.with(requireContext())
                .add("Trending", TrendingFragment::class.java)
                .add("MyRepositories", MyRepositoryFragment::class.java)
                .create()
        )
        mBinding.viewpager.adapter = adapter
        mBinding.viewpagertab.setViewPager(mBinding.viewpager)
    }
}