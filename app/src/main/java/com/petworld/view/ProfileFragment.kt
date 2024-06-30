package com.petworld.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.petworld.databinding.FragmentProfileBinding
import com.petworld.model.PetWorld
import com.petworld.model.User
import com.petworld.viewmodel.UserViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ProfileFragment : Fragment(),UserSaveChangesClick {
    private lateinit var viewModel: UserViewModel
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
        var shared: SharedPreferences = requireActivity().getSharedPreferences(
            sharedFile,
            Context.MODE_PRIVATE
        )
        var user_id = shared.getInt("user_id", 0)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch(user_id)
        observeViewModel()
        // binding.saveClickListener = this
        binding.btnSave.setOnClickListener {
            var firstName = binding.txtFirst.text.toString()
            var lastName = binding.txtLast.text.toString()
            var password = binding.txtPassword.text.toString()
            if (password.isNotEmpty()) {
                viewModel.update(firstName, lastName, password, user_id)
                Toast.makeText(view.context, "User has updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
            binding.btnLogOut.setOnClickListener{
                val editor = requireActivity().getSharedPreferences("com.PetWorld", Context.MODE_PRIVATE).edit()
                editor.remove("user_id")
                editor.remove("user")
                editor.apply()
                val action = ProfileFragmentDirections.actionItemProfileToSignInFragment()
                Navigation.findNavController(it).navigate(action)
            }

            /*    super.onViewCreated(view, savedInstanceState)
        var sharedFile = "com.PetWorld"
        var shared: SharedPreferences = requireActivity().getSharedPreferences(sharedFile,
            Context.MODE_PRIVATE )
        var user_id = shared.getInt("user_id",0)
        var username = shared.getString("user","")
        binding.txtProfile.text = username

        viewModel.fetch(user_id)
        observeViewModel()



        binding.btnSave.setOnClickListener {
            var firstName = binding.txtFirst.text.toString()
            var lastName = binding.txtLast.text.toString()
            var password = binding.txtPassword.text.toString()
            if (password.isNotEmpty())
            {
                viewModel.update(firstName, lastName, password, user_id)
                Toast.makeText(view.context, "User has updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }*/
        }
    }
    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            binding.user = it
//            if (it != null) {
//                binding.txtFirst.setText(it.first)
//                binding.txtLast.setText(it.last)
//                binding.txtPassword.setText(it.password)
//            }
        })
    }

    override fun onSaveChangesClick(v: View, obj: User) {
        viewModel.update(obj.first.toString(), obj.last.toString(), obj.password.toString(), obj.id)
        Toast.makeText(context, "Berhasil Save", Toast.LENGTH_SHORT).show()
        Log.d("cekvar", obj.toString())
    }
}