package com.example.homework16.fragments.home

import android.annotation.SuppressLint
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework16.databinding.CarItemBinding
import com.example.homework16.entity.CarModel
import com.example.homework16.extensions.setImage

typealias itemDeleteClick = (car: CarModel) -> Unit
typealias itemEditClick = (car: CarModel) -> Unit

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    lateinit var deleteClicker: itemDeleteClick
    lateinit var editClicker: itemEditClick

    private var cars = mutableListOf<CarModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder (
        CarItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind()

    }

    override fun getItemCount() = cars.size

    inner class HomeViewHolder(private val binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: CarModel
        fun onBind() {
            model = cars[adapterPosition]
            binding.tvTitle.text = model.title
            binding.tvDescription.text = model.description
            binding.ivCover.setImage(model.url)

            binding.btnDeleteCar.setOnClickListener {
                deleteClicker(cars[adapterPosition])
            }

            binding.btnEditCarInfo.setOnClickListener {
                editClicker(cars[adapterPosition])
            }
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(cars: MutableList<CarModel>) {
        this.cars.clear()
        this.cars.addAll(cars)
        notifyDataSetChanged()
    }

}