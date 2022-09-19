package com.example.animeproject.presentation.setting.registrate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.animeproject.databinding.FragmentRegistrationBinding
import moxy.MvpAppCompatFragment

class RegistrationFragment: MvpAppCompatFragment() {

    lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.btnLogIn.setOnClickListener {
//            val log = binding.etRepeatPassword.text
//            val pas = binding.etPassword.text
//            if(log !== pas){
//
//            }else{
//                Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}