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
import com.petworld.databinding.FragmentProfileBinding
import com.petworld.databinding.FragmentSignInBinding
import com.petworld.viewmodel.SignInViewModel

class ProfileFragment : Fragment() {
    private lateinit var viewModel: SignInViewModel
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedFile = "com.PetWorld"
        var shared: SharedPreferences = requireActivity().getSharedPreferences(sharedFile,
            Context.MODE_PRIVATE )
        var username = shared.getString("user","")
        binding.txtProfile.text = username
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        viewModel.fetch()
        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            user.forEach {
                if (it.username == username.toString()){
                    binding.txtFirst.setText(it.first)
                    binding.txtLast.setText(it.last)
                    binding.txtPassword.setText(it.password)
                }
            }
        })
        binding.btnLogOut.setOnClickListener{
            val editor = requireActivity().getSharedPreferences("com.PetWorld", Context.MODE_PRIVATE).edit()
            editor.remove("user")
            editor.apply()
            val action = ProfileFragmentDirections.actionItemProfileToSignInFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}