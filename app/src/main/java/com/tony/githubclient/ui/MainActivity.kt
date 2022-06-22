package com.tony.githubclient.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tony.githubclient.R
import com.tony.githubclient.base.BaseActivity
import com.tony.githubclient.databinding.ActivityMainBinding
import com.tony.githubclient.ui.account.AccountFragment
import com.tony.githubclient.ui.home.HomeFragment

class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private var showIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mBinding.handler = this
        onHomeClick()
    }

    fun onHomeClick() {
        if (showIndex == 1) {
            return
        }
        switchSelect(1)
        showIndex = 1
        hideFragment(AccountFragment.TAG)
        showFragment(HomeFragment.TAG) {
            HomeFragment()
        }
    }

    private fun switchSelect(index: Int) {
        when (index) {
            1 -> {
                mBinding.ivHome.isSelected = true
                mBinding.tvHome.isSelected = true
                mBinding.ivAccount.isSelected = false
                mBinding.tvAccount.isSelected = false
            }
            2 -> {
                mBinding.ivHome.isSelected = false
                mBinding.tvHome.isSelected = false
                mBinding.ivAccount.isSelected = true
                mBinding.tvAccount.isSelected = true
            }
        }
    }

    fun onAccountClick() {
        if (showIndex == 2) {
            return
        }
        switchSelect(2)
        showIndex = 2
        hideFragment(HomeFragment.TAG)
        showFragment(AccountFragment.TAG) {
            AccountFragment()
        }
    }

    private fun showFragment(tag: String, newInstance: () -> Fragment) {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fl_main, fragment, tag).commitNow()
        }
        supportFragmentManager.beginTransaction().show(fragment).commitNow()
    }

    private fun hideFragment(tag: String) {
        supportFragmentManager.findFragmentByTag(tag)?.let {
            supportFragmentManager.beginTransaction().hide(it).commitNow()
        }
    }

    fun switchFragment(index: Int) {

    }


}