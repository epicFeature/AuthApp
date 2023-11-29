package com.authapp.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.authapp.databinding.FragmentMainBinding

object AuthValidation {

    fun logInButtonEnable() {

    }

    fun loginInputProcessing(fragment: Fragment, binding: FragmentMainBinding): Boolean {
        var success = false
        binding.loginEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                when (loginInputValidation(binding)) {
                    true -> {
                        binding.loginContainer.helperText = ""
                        success = true
                    }

                    false -> {
                        binding.button.isEnabled = false
                        binding.loginContainer.helperText = "Write your login"
                        Toast.makeText(
                            fragment.requireContext(),
                            "Field login is empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                binding.button.isEnabled = false
                binding.loginContainer.helperText = ""
            }
        }
        return success
    }

    private fun loginInputValidation(binding: FragmentMainBinding): Boolean {
        return !binding.loginEditText.text.isNullOrEmpty()
    }

    fun loginResponseProcessing() {

    }

    fun loginResponseValidation() {

    }

    fun passwordInputProcessing(fragment: Fragment, binding: FragmentMainBinding):Boolean {
        var success = false
        binding.passwordEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                when (passwordInputValidation(binding)) {
                    true -> {
                        binding.passwordContainer.helperText = ""
                        success = true
                    }

                    false -> {
                        binding.button.isEnabled = false
                        binding.passwordContainer.helperText = "Write your password"
                        Toast.makeText(
                            fragment.requireContext(),
                            "Field password is empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                binding.button.isEnabled = false
                binding.passwordContainer.helperText = ""
            }
        }
        return success
    }

    fun passwordInputValidation(binding: FragmentMainBinding): Boolean {
        return !binding.loginEditText.text.isNullOrEmpty()
    }


fun passwordResponseProcessing() {

}

fun passwordResponseValidation() {

}
}