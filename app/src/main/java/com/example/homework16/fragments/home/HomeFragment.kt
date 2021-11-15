package com.example.homework16.fragments.home

import android.app.AlertDialog
import android.view.ViewGroup

import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework16.R
import com.example.homework16.databinding.EditCarLayoutBinding
import com.example.homework16.databinding.FragmentHomeBinding

import com.example.homework16.fragments.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun start() {
        initHomeRecycler()
        listeners()
        observes()
        deleteCar()
        updateCar()
    }

    private fun initHomeRecycler() {
        homeAdapter = HomeAdapter()
        binding.carsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
    }

    private fun deleteCar() {
        homeAdapter.deleteClicker = {
            viewModel.deleteCar(it)
        }
    }

    private fun updateCar() {
        homeAdapter.editClicker = {
            val builder = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_car_layout, null)

            val title = dialogLayout.findViewById<EditText>(R.id.etChangeTitle)
            val description = dialogLayout.findViewById<EditText>(R.id.etChangeDescription)
            val url = dialogLayout.findViewById<EditText>(R.id.etChangeUrl)
            title.setText(it.title)
            description.setText(it.description)
            url.setText(it.url)

            with(builder) {
                setTitle("UPDATE")
                setPositiveButton("UPDATE") { dialog, which ->
                    viewModel.updateCar(it.id, title.text.toString(), description.text.toString(), url.text.toString())
                }
                setNegativeButton("CANCEL") { dialog, which ->
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    private fun listeners() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddCarFragment())
        }

    }

    private fun observes() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cars.collect {
                    if(it.isNullOrEmpty()) {
                        binding.tvItemsIsEmpty.text = "Items is Empty"
                    } else {
                        homeAdapter.setData(it.toMutableList())
                    }

                }
            }
        }
    }


}