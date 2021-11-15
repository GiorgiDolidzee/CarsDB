package com.example.homework16.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework16.database.AppDataBase
import com.example.homework16.entity.CarModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    val cars: Flow<List<CarModel>> = AppDataBase.db.carDao().getAll()

    fun deleteCar(car: CarModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                AppDataBase.db.carDao().delete(car)
            }
        }
    }

    fun updateCar(id: Int, title: String, description: String, url: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if(title.length in 5..30 && description.length in 32..300) {
                    AppDataBase.db.carDao().updateCar(CarModel(id, title, description, url))
                }
            }
        }
    }



}