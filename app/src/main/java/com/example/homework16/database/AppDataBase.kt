package com.example.homework16.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homework16.App
import com.example.homework16.dao.CarDao
import com.example.homework16.entity.CarModel

@Database(entities = [CarModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun carDao() : CarDao

    companion object {
        val db by lazy {
            Room.databaseBuilder(
                App.appContext!!,
                AppDataBase::class.java, "car_db"
            ).build()
        }
    }
}