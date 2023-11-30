package com.authapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.authapp.api.auth.AuthRepository
import com.authapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var repository: AuthRepository
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository=AuthRepository(this)
    }
}