package com.authapp.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.authapp.R
import com.authapp.api.auth.AuthInfo
import com.authapp.api.auth.AuthProfileData
import com.authapp.api.auth.AuthRepository
import com.authapp.api.auth.AuthService
import com.authapp.databinding.FragmentMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainFragment : Fragment() {
    private lateinit var authService: AuthService

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        authService = AuthService(AuthRepository(this.requireContext().applicationContext))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginInputProcessing()
        passwordInputProcessing()

        binding.logInButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            makeApiCall(login, password)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loginInputProcessing() {
        binding.loginEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.logInButton.isEnabled = false
                binding.loginContainer.helperText = ""
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginInputValidation()
            }
            override fun afterTextChanged(s: Editable?) {
                logInButtonEnabling()
            }
        })
    }

    private fun loginInputValidation() {
        when (binding.loginEditText.text.isNullOrEmpty()) {
            true -> {
                binding.loginContainer.helperText = "Write your login"
                Toast.makeText(
                    this.requireContext(),
                    "Field login is empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
            false -> {
                binding.loginContainer.helperText = ""
            }
        }
    }

    private fun passwordInputProcessing() {
        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.logInButton.isEnabled = false
                binding.passwordContainer.helperText = ""
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passwordInputValidation()
            }

            override fun afterTextChanged(s: Editable?) {
                logInButtonEnabling()
            }
        })
    }

    private fun passwordInputValidation() {
        when (binding.passwordEditText.text.isNullOrEmpty()) {
            true -> {
                binding.passwordContainer.helperText = "Write your password"
                Toast.makeText(
                    this.requireContext(),
                    "Field password is empty",
                    Toast.LENGTH_SHORT
                ).show()
            }

            false -> binding.passwordContainer.helperText = ""
        }
    }

    private fun logInButtonEnabling() {
        if (
            !binding.passwordEditText.text.isNullOrEmpty()
            && !binding.loginEditText.text.isNullOrEmpty()
        ) {
            binding.logInButton.isEnabled = true
            binding.topImage.setImageResource(R.drawable.image_boy_in_blue)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun makeApiCall(login: String, password: String) =
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val result = authService.getAuthData(AuthProfileData(login, password)).body()!!
                dataMapping(result)
            } catch (e: Exception) {
                Log.e("httpCall", e.message ?: "Unknown Error")
            }
        }

    private fun dataMapping(result: AuthInfo) {
        if (result.success.toBoolean()) {
            result.tokenInfo?.let { authService.saveToken(it.token) }
            findNavController().navigate(R.id.action_mainFragment_to_paymentListFragment)
        } else {
            onWrongLoginPassword()
        }
    }

    private fun onWrongLoginPassword() {
        Toast.makeText(
            this.requireContext(),
            "You haven't filled login or password.",
            Toast.LENGTH_SHORT
        ).show()
        with(binding) {
            logInButton.isEnabled = false
            binding.topImage.setImageResource(R.drawable.image_boy_in_red)
            loginOutputValidation()
            passwordOutputValidation()
        }
    }

    private fun loginOutputValidation() {
        return when (binding.loginEditText.text.isNullOrEmpty()) {
            true -> {
                binding.loginContainer.helperText = "Write your login"
            }
            false -> binding.loginContainer.helperText = "Wrong login"
        }
    }

    private fun passwordOutputValidation() {
        when (binding.passwordEditText.text.isNullOrEmpty()) {
            true -> {
                binding.passwordContainer.helperText = "Write your password"
            }
            false -> binding.passwordContainer.helperText = "Wrong password"

        }
    }
}