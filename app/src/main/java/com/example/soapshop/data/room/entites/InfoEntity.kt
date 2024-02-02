package com.example.soapshop.data.room.entites

import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soapshop.domain.models.catalog.InfoModel

@Stable
@Entity(tableName = "info")
data class InfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val value: String = ""
) {
    fun toInfoModel() = InfoModel(
        title, value
    )
}
