package com.petworld.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.petworld.R
import com.petworld.databinding.FragmentCreateBinding
import com.petworld.databinding.FragmentCreateDetailBinding
import com.petworld.viewmodel.DetailViewModel
import com.petworld.viewmodel.ListViewModel

class CreateDetailFragment : Fragment() {
    private lateinit var binding: FragmentCreateDetailBinding
    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

//        binding.btnAdd.setOnClickListener {
//            var todo = Todo(
//                binding.txtTitle.text.toString(),
//                binding.txtNotes.text.toString()
//            )
//            val list = listOf(todo)
//            viewModel.addTodo(list)
//            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
//            Navigation.findNavController(it).popBackStack()
//        }

    }


}