package com.example.homework16.fragments.addcar

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homework16.databinding.FragmentAddCarBinding
import com.example.homework16.fragments.base.BaseFragment

class AddCarFragment : BaseFragment<FragmentAddCarBinding>(FragmentAddCarBinding::inflate) {

    private val viewModel: AddCarViewModel by viewModels()

    override fun start() {
        listeners()
    }

    private fun listeners() {
        binding.btnAddCar.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDescription.text.toString()
            val url = binding.etUrl.text.toString()
            viewModel.addCar(title, desc, url)
            findNavController().popBackStack()
        }
    }


}