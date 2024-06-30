package com.petworld.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.petworld.R
import com.petworld.databinding.FragmentCreateBinding
import com.petworld.databinding.FragmentCreateDetailBinding
import com.petworld.model.DetailPetWorld
import com.petworld.viewmodel.DetailViewModel
import com.petworld.viewmodel.ListViewModel

class CreateDetailFragment : Fragment() {
    private lateinit var binding: FragmentCreateDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val id = CreateDetailFragmentArgs.fromBundle(requireArguments()).id
        binding.btnDone.setOnClickListener() {
            var detailPetWorld = DetailPetWorld(
                id,
                binding.txtSubtitle.text.toString(),
                binding.txtParagraf.text.toString()
            )
            val list = listOf(detailPetWorld)
            viewModel.addDetailPetWorld(list)

            val action = CreateDetailFragmentDirections.actionCreateDetailFragmentToItemHome()
            Navigation.findNavController(it).navigate(action)
        }

    }


}