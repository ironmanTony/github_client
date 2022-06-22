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

    val homeTabUrl = mapOf(
        "Explore" to "https://github.com/explore",
        "Trend" to "https://github.com/trending",
        "Events" to "https://github.com/events",
        "Sponsors" to "https://github.com/sponsors/explore",
    )

    override fun getDataBindingClass(): Class<FragmentHomeBinding> {
        return FragmentHomeBinding::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val fragmentItems = FragmentPagerItems.with(requireContext())
        homeTabUrl.entries.forEach { entry ->
            fragmentItems.add(
                entry.key,
                TrendingFragment::class.java,
                Bundle().apply { putString("url", entry.value) })
        }
        val adapter = FragmentPagerItemAdapter(
            childFragmentManager,
            fragmentItems.create()
        )
        mBinding.viewpager.adapter = adapter
        mBinding.viewpagertab.setViewPager(mBinding.viewpager)
    }
}