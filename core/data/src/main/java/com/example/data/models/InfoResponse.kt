package com.example.data.models

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("title") val title: String = "",
    @SerializedName("value") val value: String = ""
)
