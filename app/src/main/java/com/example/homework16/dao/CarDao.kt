package com.example.homework16.dao

import androidx.room.*
import com.example.homework16.entity.CarModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Query("SELECT * FROM CARS")
    fun getAll(): Flow<List<CarModel>>

    @Query("SELECT * FROM CARS WHERE title LIKE (:title) LIMIT 1")
    fun getByTitle(title: String) : CarModel


    @Insert
    suspend fun insertAll(vararg items: CarModel)

    @Delete
    fun delete(items: CarModel)

    @Update
    fun updateCar(items: CarModel)
}