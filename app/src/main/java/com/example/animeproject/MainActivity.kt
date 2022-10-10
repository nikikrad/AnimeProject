package com.example.animeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.TooltipCompat
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.animeproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.findNavController()
        val bottomNavigationView = binding.bnvNavigation
//        bottomNavigationView
//            .menu.forEach {
//                TooltipCompat.setTooltipText(bottomNavigationView.findViewById(it.itemId), null)
//            }
        bottomNavigationView
            .menu.forEach {
                bottomNavigationView.findViewById<View>(it.itemId).setOnLongClickListener {
                    // add any code which you want to execute on long click
                    true
                }
            }
        bottomNavigationView.setupWithNavController(navController)
    }
}