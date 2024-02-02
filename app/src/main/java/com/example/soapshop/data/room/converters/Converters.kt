package com.example.soapshop.data.room.converters

import androidx.room.TypeConverter
import com.example.soapshop.data.room.entites.InfoEntity
import com.google.gson.Gson

class Converters {

    private val gson = Gson()
    @TypeConverter
    fun fromInfoList(value: InfoList): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toInfoList(value: String): InfoList {
        return gson.fromJson(value, InfoList::class.java)
    }

    @TypeConverter
    fun fromStringList(value: TagList): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): TagList {
        return gson.fromJson(value, TagList::class.java)
    }


}

data class TagList(
    val tags: List<String> = listOf()
)

data class InfoList(
    val list: List<InfoEntity> = listOf()
)