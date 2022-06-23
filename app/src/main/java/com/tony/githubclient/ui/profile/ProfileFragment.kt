package com.tony.githubclient.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.tony.githubclient.base.BaseFragment
import com.tony.githubclient.databinding.FragmentProfileBinding
import com.tony.githubclient.utils.LoginUtils

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    companion object {
        const val TAG = "AccountFragment"
    }

    private val mViewModel: ProfileViewModel by viewModels()

    override fun getDataBindingClass(): Class<FragmentProfileBinding> {
        return FragmentProfileBinding::class.java
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.mUserLiveData.observe(viewLifecycleOwner) {
            mBinding.data = mViewModel.mUserInfo
        }
        if (LoginUtils.isLogin()) {
            mViewModel.getUserInfo()
        }
    }
}