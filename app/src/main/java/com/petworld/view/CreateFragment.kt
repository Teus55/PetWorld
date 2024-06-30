package com.petworld.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.petworld.R
import com.petworld.databinding.FragmentCreateBinding
import com.petworld.model.PetWorld
import com.petworld.viewmodel.ListViewModel

class CreateFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        var sharedFile = "com.PetWorld"
        var shared: SharedPreferences = requireActivity().getSharedPreferences(
            sharedFile,
            Context.MODE_PRIVATE
        )
        var user_id = shared.getInt("user_id", 0)
        var username = shared.getString("user", "")
        binding.btnNext.setOnClickListener() {

            var petWorld = PetWorld(
                binding.txtJudul.text.toString(),
                username,
                binding.txtShortParaf.text.toString(),
                binding.txtUrl.text.toString(),
                binding.txtCategory.text.toString()
            )

            val list = listOf(petWorld)
            viewModel.addPetWorld(list)
            viewModel.idPetLD.observe(viewLifecycleOwner, Observer { id ->
                val action = CreateFragmentDirections.actionCreateDetail(id.toString())
                Navigation.findNavController(it).navigate(action)
            })


        }

    }
}

