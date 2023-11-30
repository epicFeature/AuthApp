package com.authapp.ui.paymentlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.authapp.R
import com.authapp.api.auth.AuthRepository
import com.authapp.databinding.FragmentPaymentBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PaymentFragment : Fragment() {
    private lateinit var viewModel: PaymentViewModel
    private lateinit var recyclerAdapter: PaymentRecyclerViewAdapter

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initViewModel(AuthRepository(this.requireContext().applicationContext))

        binding.logOutButton.setOnClickListener {
            logOut(AuthRepository(this.requireContext().applicationContext))
            findNavController().navigate(R.id.action_paymentListFragment_to_mainFragment)// проверить!!!
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun logOut(repository: AuthRepository) {
        repository.deleteToken()
    }

    private fun initRecyclerView() {
        binding.paymentRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerAdapter = PaymentRecyclerViewAdapter()
        binding.paymentRecyclerView.adapter = recyclerAdapter
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initViewModel(repository: AuthRepository)  {
        viewModel = ViewModelProvider(this@PaymentFragment)[PaymentViewModel::class.java]
        val token = repository.getToken()
        viewModel.paymentLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setData(it)
            } else {
                Toast.makeText(
                    this@PaymentFragment.requireContext(),
                    "Ой, что-то пошло не так, попробуйте позже.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.makeApiCall(token!!)
    }
}
