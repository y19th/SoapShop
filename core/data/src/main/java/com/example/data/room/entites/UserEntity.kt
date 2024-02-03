package com.example.data.room.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "surname") val surname: String = "",
    @ColumnInfo(name = "phone") val phone: String = ""
)
