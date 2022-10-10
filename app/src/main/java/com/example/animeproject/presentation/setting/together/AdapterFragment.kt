package com.example.animeproject.presentation.setting.together

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.animeproject.databinding.FragmentAdapterBinding
import com.google.android.material.tabs.TabLayout

class AdapterFragment: Fragment() {

    private lateinit var binding: FragmentAdapterBinding
    private lateinit var myFragmentAdapter: MyFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdapterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        binding.tabLayout(binding.tabLayout.newTab().text = "Регистрация")
        binding.tabLayout.newTab().text = "Авторизация"
        binding.tabLayout.newTab().text = "Регистрация"
        myFragmentAdapter = MyFragmentAdapter(childFragmentManager, lifecycle)
        binding.viewPager2.adapter = myFragmentAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager2.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }
}