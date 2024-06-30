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
import com.petworld.viewmodel.UserViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ProfileFragment : Fragment() {
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
        var shared: SharedPreferences = requireActivity().getSharedPreferences(sharedFile,
            Context.MODE_PRIVATE )
        var username = shared.getString("user","")
        binding.txtProfile.text = username
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
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

        binding.btnSave.setOnClickListener {
            var firstName = binding.txtFirst.text.toString()
            var lastName = binding.txtLast.text.toString()
            var password = binding.txtPassword.text.toString()
            update(username.toString(), firstName, lastName, password)
        }
    }

    fun update(username: String, firstName: String, lastName: String, password: String) {
        val url = "http://10.0.2.2/uts_anmp/update.php"

        val requestBody = FormBody.Builder()
            .add("username", username)
            .add("firstName", firstName)
            .add("lastName", lastName)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                // Penanganan jika gagal melakukan registrasi
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body()?.string()
                    // Proses tanggapan dari server setelah registrasi berhasil
                } else {
                    // Penanganan jika gagal melakukan registrasi
                }
            }
        })
    }
}