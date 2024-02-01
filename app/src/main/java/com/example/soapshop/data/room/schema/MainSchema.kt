package com.example.soapshop.data.room.schema

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.soapshop.data.room.dao.MainDao
import com.example.soapshop.data.room.entites.UserEntity


@Database(entities = [UserEntity::class], version = 1)
abstract class MainSchema: RoomDatabase() {

    abstract fun mainDao(): MainDao

    companion object {
        @Volatile
        private var INSTANCE: MainSchema? = null

        fun receiveInstance(context: Context): MainSchema {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainSchema::class.java,
                    "main_schema"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}