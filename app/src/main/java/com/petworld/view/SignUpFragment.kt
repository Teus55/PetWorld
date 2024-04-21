package com.petworld.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.petworld.R
import com.petworld.databinding.FragmentSignInBinding
import com.petworld.databinding.FragmentSignUpBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
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
        binding.btnSignUp.setOnClickListener {
            var username = binding.txtUsername.text.toString()
            var firstName = binding.txtFirst.text.toString()
            var lastName = binding.txtLast.text.toString()
            var password = binding.txtPassword.text.toString()
            register(username, firstName, lastName, password)

            val action = SignUpFragmentDirections.actionSignInFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun register(username: String, firstName: String, lastName: String, password: String) {
        val url = "http://10.0.2.2/uts_anmp/register.php"

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