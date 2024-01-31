package com.example.soapshop.room.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soapshop.domain.models.UserModel

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "surname") val surname: String = "",
    @ColumnInfo(name = "phone") val phone: String = ""
) {
    fun toUserModel() = UserModel(
        id, name, surname, phone
    )
}
