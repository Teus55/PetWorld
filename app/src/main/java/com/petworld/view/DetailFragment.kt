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
    var maxPage = 0
    var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val idBerita =
                DetailFragmentArgs.fromBundle(requireArguments()).idBerita
            val imgURL =
                DetailFragmentArgs.fromBundle(requireArguments()).imgUrl
            val judul =
                DetailFragmentArgs.fromBundle(requireArguments()).judul
            val user =
                DetailFragmentArgs.fromBundle(requireArguments()).user

            binding.txtTitle.text = judul
            binding.txtUser.text = user
            Picasso.get().load(imgURL).into(binding.imgBerita)

            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch()
            viewModel.detailPetWorldLD.observe(viewLifecycleOwner, Observer {
                    detail ->
                detail.forEach{
                    if (it.idPetworld == idBerita){
                        maxPage++
                        if (it.id == page.toString()){
                            binding.txtPara.text = it.para

                        }
                    }
                }
                binding.txtPage.text = "$page / $maxPage"
            })
            binding.btnNext.setOnClickListener{
                if (page != maxPage){
                    page = page + 1
                    binding.txtPage.text = "$page / $maxPage"
                    observeViewModel(idBerita, page)
                }
            }
            binding.btnBefore.setOnClickListener{
                if (page != 1){
                    page--
                    binding.txtPage.text = "$page / $maxPage"
                    observeViewModel(idBerita, page)
                }
            }

        }
    }
    fun observeViewModel(idBerita: String, page: Int) {
        viewModel.detailPetWorldLD.observe(viewLifecycleOwner, Observer {
                detail ->
            detail.forEach{
                if (it.idPetworld == idBerita){

                    if (it.id == page.toString()){
                        binding.txtPara.text = it.para
                    }
                }
            }

        })
    }
}