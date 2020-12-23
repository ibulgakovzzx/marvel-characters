package com.example.marver_characters.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*

class CharacterConverter {

    companion object {
        private val gson: Gson = GsonBuilder().create()
    }

    @TypeConverter
    fun convertStringToList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun convertListToString(values: List<String>): String {
        return gson.toJson(values)
    }

    @TypeConverter
    fun convertDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertLongToDate(value: Long): Date {
        return Date(value)
    }

}