package com.example.animeproject.presentation.setting.registrate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.animeproject.databinding.FragmentRegistrationBinding
import com.example.animeproject.presentation.setting.request.UserRequest
import com.example.animeproject.presentation.setting.response.DataResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import moxy.MvpAppCompatFragment


class RegistrationFragment : MvpAppCompatFragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

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
        database = Firebase.database.reference
        binding.btnLogIn.setOnClickListener {

            if (binding.etRepeatPassword.text.toString() == binding.etPassword.text.toString()) {
                if (binding.etPassword.text.toString().length < 6) {
                    Toast.makeText(
                        context,
                        "Пароль должен быть больше 6 символов",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    writeNewUser("2", binding.etLogin.text.toString())
                    registerUser()
                }
            } else {
                Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun writeNewUser(userId: String, email: String) {
        val user = UserRequest(userId, email)
        database.child("users").child(userId).setValue(user)
        database.child("users").get().addOnSuccessListener {
//            Log.e("TAG", it.toString() )
//            Log.e("TAG", it.)
            val userList: DataResponse = it.value as DataResponse
            Log.e("TAG", userList.email)


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