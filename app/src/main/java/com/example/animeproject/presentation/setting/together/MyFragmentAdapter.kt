package com.example.animeproject.presentation.setting.together

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.animeproject.presentation.setting.login.LoginFragment
import com.example.animeproject.presentation.setting.registrate.RegistrationFragment


class MyFragmentAdapter(
    fragmentManager: FragmentManager?,
    lifecycle: Lifecycle?
) :
    FragmentStateAdapter(fragmentManager!!, lifecycle!!) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            RegistrationFragment()
        } else LoginFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}