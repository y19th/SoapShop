package com.example.soapshop.data.models

import com.example.soapshop.domain.models.catalog.InfoModel
import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("title") val title: String = "",
    @SerializedName("value") val value: String = ""
) {
    fun toInfoModel() = InfoModel(title, value)
}
