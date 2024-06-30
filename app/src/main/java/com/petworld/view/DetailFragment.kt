package com.petworld.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.petworld.R
import com.petworld.databinding.FragmentDetailBinding
import com.petworld.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding
//    var maxPage = 0
//    var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = DetailFragmentArgs.fromBundle(requireArguments()).idBerita.toInt()
        val user = DetailFragmentArgs.fromBundle(requireArguments()).user
        val imgURL = DetailFragmentArgs.fromBundle(requireArguments()).imgUrl
        binding.txtUser.text = user
        Picasso.get().load(imgURL).into(binding.imgBerita)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.detailPetWorldLD.observe(viewLifecycleOwner, Observer {
            binding.txtPara.setText(it.para)
            binding.txtTitle.setText(it.subtitle)
        })
    }
}