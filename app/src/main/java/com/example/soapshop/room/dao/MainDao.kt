package com.example.soapshop.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.soapshop.room.entites.UserEntity

@Dao
interface MainDao {

    @Query("select * from users")
    fun receiveUsers() : List<UserEntity>

    @Insert
    fun insertUsers(vararg users: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)
}