package com.example.soapshop.data.room.schema

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.soapshop.data.room.converters.Converters
import com.example.soapshop.data.room.dao.ProductDao
import com.example.soapshop.data.room.dao.UserDao
import com.example.soapshop.data.room.entites.FeedbackEntity
import com.example.soapshop.data.room.entites.PriceEntity
import com.example.soapshop.data.room.entites.ProductEntity
import com.example.soapshop.data.room.entites.UserEntity


@Database(
    entities = [UserEntity::class, ProductEntity::class, PriceEntity::class, FeedbackEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MainSchema: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao

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