package com.petworld.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.petworld.R
import com.petworld.databinding.FragmentSignInBinding
import com.petworld.databinding.FragmentSignUpBinding
import com.petworld.model.User
import com.petworld.viewmodel.UserViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnSignUp.setOnClickListener {
            var user = User(
                binding.txtFirst.text.toString(),
                binding.txtLast.text.toString(),
                binding.txtUsername.text.toString(),
                binding.txtPassword.text.toString()
            )

            val list = listOf(user)
            val idPet =  viewModel.register(list)
            Toast.makeText(view.context, "User added", Toast.LENGTH_LONG).show()

            val action = SignUpFragmentDirections.actionSignInFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}