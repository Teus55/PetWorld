package com.petworld.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.petworld.R
import com.petworld.databinding.FragmentCreateBinding
import com.petworld.viewmodel.ListViewModel

class CreateFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding
    private lateinit var viewModel:ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateBinding.inflate(inflater,container,false)
        return binding.root
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

    }
}