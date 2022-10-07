package com.example.animeproject.presentation.setting.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.animeproject.R
import com.example.animeproject.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import moxy.MvpAppCompatFragment
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : MvpAppCompatFragment() {

    private lateinit var binding: FragmentLoginBinding
    @Inject
    lateinit var auth: FirebaseAuth
    @Inject
    lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        checkLoggedInState()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnRegistration.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.btnLogOut.setOnClickListener {
            auth.signOut()
            checkLoggedInState()
        }
        binding.btnLogIn.setOnClickListener {
            logIn()
        }

    }

    private fun logIn() {
        CoroutineScope(Dispatchers.Main).launch {
            if (binding.etLogin.text !== null && binding.etPassword.text.length >= 6) {

                auth.signInWithEmailAndPassword(
                    binding.etLogin.text.toString(),
                    binding.etPassword.text.toString()
                ).addOnFailureListener { binding.tvErrorLabel.isVisible = true }
                delay(1100)
                withContext(Dispatchers.Main) {
                    checkLoggedInState()
                }
            } else {
                Toast.makeText(context, "Неправильный ввод данных!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkLoggedInState() {
        if (auth.currentUser !== null) {
            binding.tvSignInLabel.text = "Вы онлайн!"
            binding.tvEmail.text = auth.currentUser?.email.toString()
            binding.etLogin.isVisible = false
            binding.etPassword.isVisible = false
            binding.btnLogIn.isVisible = false
            binding.btnRegistration.isVisible = false
            binding.tvErrorLabel.isVisible = false
            binding.btnLogOut.isVisible = true
        } else {
            binding.tvSignInLabel.text = "Войдите в аккаунт"
            binding.etLogin.isVisible = true
            binding.etPassword.isVisible = true
            binding.btnLogIn.isVisible = true
            binding.btnRegistration.isVisible = true
            binding.tvEmail.isVisible = false
            binding.btnLogOut.isVisible = false
        }
    }


}