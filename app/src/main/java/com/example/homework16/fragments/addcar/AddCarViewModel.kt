package com.example.homework16.fragments.addcar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework16.database.AppDataBase
import com.example.homework16.entity.CarModel
import kotlinx.coroutines.launch

class AddCarViewModel : ViewModel() {

    fun addCar(title: String, description: String, url: String) {
        if(title.isNotEmpty() && description.isNotEmpty() && url.isNotEmpty()) {
            if(title.length in 5..30 && description.length in 32..300) {
                viewModelScope.launch {
                    AppDataBase.db.carDao().insertAll(CarModel(0, title, description, url))
                }
            }
        }
    }

}