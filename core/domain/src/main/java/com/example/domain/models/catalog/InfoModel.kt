package com.example.domain.models.catalog

import androidx.compose.runtime.Stable
import com.example.data.models.InfoResponse
import com.example.data.room.entites.InfoEntity

@Stable
data class InfoModel(
    val title: String = "",
    val value: String = ""
) {
    fun toInfoEntity() = InfoEntity(title = title, value = value)
}

fun InfoResponse.toInfoModel() = InfoModel(title, value)

fun InfoEntity.toInfoModel() = InfoModel(title, value)
