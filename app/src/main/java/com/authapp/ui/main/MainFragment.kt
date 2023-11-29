package com.authapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.authapp.R
import com.authapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    /*private var loginState: Boolean
    private var passwordState: Boolean*/

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*loginProcessing().also { loginState = it }
        passwordProcessing().also { passwordState = it }*/
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_paymentListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}