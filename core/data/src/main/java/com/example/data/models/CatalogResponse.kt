package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CatalogResponse(
    @SerializedName("items") val items: List<ProductResponse> = listOf()
)
