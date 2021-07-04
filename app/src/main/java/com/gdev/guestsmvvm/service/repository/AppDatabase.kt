package com.gdev.guestsmvvm.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdev.guestsmvvm.service.model.GuestModel
import com.gdev.guestsmvvm.service.repository.dao.GuestDao

@Database(entities = [GuestModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun guestDao():GuestDao

    companion object {
        private lateinit var INSTANCE: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "guestDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}