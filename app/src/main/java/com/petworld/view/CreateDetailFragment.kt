package com.petworld.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petworld.R
import com.petworld.databinding.FragmentCreateBinding
import com.petworld.databinding.FragmentCreateDetailBinding

class CreateDetailFragment : Fragment() {
    private lateinit var binding: FragmentCreateDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateDetailBinding.inflate(inflater,container,false)
        return binding.root

    }

}