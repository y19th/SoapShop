package com.example.soapshop.domain.models.catalog

import androidx.compose.runtime.Stable
import com.example.soapshop.data.room.entites.InfoEntity

@Stable
data class InfoModel(
    val title: String = "",
    val value: String = ""
) {
    fun toInfoEntity() = InfoEntity(title = title, value = value)
}
