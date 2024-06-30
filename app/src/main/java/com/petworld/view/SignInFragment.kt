package com.petworld.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
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
        viewModel.fetch()
        binding.btnSignIn.setOnClickListener {
            viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
                user.forEach {
                    if ((it.username == binding.txtUsername.text.toString()) &&
                        (it.password == binding.txtPassword.text.toString())
                    ) {
                        cekLogin = 1
                    }
                }
            })
            if (cekLogin == 1) {
                var sharedFile = "com.PetWorld"
                var shared: SharedPreferences = requireActivity().getSharedPreferences(sharedFile,
                    Context.MODE_PRIVATE )
                var editor: SharedPreferences.Editor = shared.edit()
                editor.putString("user", binding.txtUsername.text.toString())
                editor.apply()
                val action = SignInFragmentDirections.actionSignInFragmentToItemHome2()
                Navigation.findNavController(it).navigate(action)
            }
        }
        binding.btnCreate.setOnClickListener{
            val action = SignInFragmentDirections.actionSignUpFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}