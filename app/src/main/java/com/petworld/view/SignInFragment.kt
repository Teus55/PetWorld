package com.petworld.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.petworld.R
import com.petworld.databinding.FragmentSignInBinding
import com.petworld.viewmodel.UserViewModel

class SignInFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentSignInBinding
    var cekLogin = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//        viewModel.fetch()
        binding.btnSignIn.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(context, "Username dan Password masih kosong", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCreate.setOnClickListener{
            val action = SignInFragmentDirections.actionSignUpFragment()
            Navigation.findNavController(it).navigate(action)
        }
        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            if (user == null) {
                Toast.makeText(context, "Username/Password Salah", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPreferences = requireActivity().getSharedPreferences("com.PetWorld", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt("user_id", user.id)
                editor.putString("user", user.username)
                editor.apply()
                findNavController().navigate(R.id.action_signInFragment_to_itemHome2)
                Toast.makeText(context, "Login successful.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}