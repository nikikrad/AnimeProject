package com.example.animeproject.presentation.setting.registrate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.animeproject.R
import com.example.animeproject.databinding.FragmentRegistrationBinding
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import java.io.FileInputStream


class RegistrationFragment : MvpAppCompatFragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()

        binding.btnLogIn.setOnClickListener {

            if (binding.etRepeatPassword.text.toString() == binding.etPassword.text.toString()) {
                if (binding.etPassword.text.toString().length < 6) {
                    Toast.makeText(
                        context,
                        "Пароль должен быть больше 6 символов",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    registerUser()
                }
            } else {
                Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser() {
        val email = binding.etLogin.text.toString()
        val password = binding.etPassword.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                withContext(Dispatchers.Main) {
                    checkLoggedInState()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkLoggedInState() {
        Toast.makeText(context, "Вы Зарегестрировались!", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(binding.root).popBackStack()
    }

}