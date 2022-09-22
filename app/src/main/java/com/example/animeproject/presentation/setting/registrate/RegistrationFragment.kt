package com.example.animeproject.presentation.setting.registrate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.animeproject.databinding.FragmentRegistrationBinding
import com.example.animeproject.presentation.setting.response.UserResponse
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
    private var usersList: MutableList<UserResponse> = emptyList<UserResponse>().toMutableList()

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
            var checkingExistingUser = false
            if (binding.etRepeatPassword.text.toString() == binding.etPassword.text.toString()) {
                if (binding.etPassword.text.toString().length < 6) {
                    Toast.makeText(
                        context,
                        "Пароль должен быть больше 6 символов",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    database.child("users").get().addOnSuccessListener {
                        it.children.forEach { data ->
                            usersList.add(
                                UserResponse(
                                    data.child("userId").value.toString(),
                                    data.child("email").value.toString()
                                )
                            )
                        }
                        val userValue = usersList.size
                        if (userValue == 0){
                            checkingExistingUser = checkExistingUser(
                                "0",
                                binding.etLogin.text.toString()
                            )
                        }else{
                            checkingExistingUser = checkExistingUser(
                                usersList[userValue - 1].userId,
                                binding.etLogin.text.toString()
                            )
                        }
                        if (checkingExistingUser)
                            registerUser()
                    }

                }
            } else {
                Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkExistingUser(userId: String, email: String): Boolean {
        usersList.forEach {
            if (email == it.email) {
                return false
            }
        }
        if (usersList.size == 0){
            database.child("users").child(userId).setValue(UserResponse(userId, email))
            return true
        }
        database.child("users").child((userId.toInt() + 1).toString()).setValue(UserResponse((userId.toInt() + 1).toString(), email))
        return true
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