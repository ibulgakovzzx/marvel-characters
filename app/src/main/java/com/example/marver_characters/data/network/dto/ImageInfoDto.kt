package com.example.marver_characters.data.network.dto

class ImageInfoDto(var path: String, var extension: String)
{
    companion object {
        const val STANDARD_MEDIUM = "standard_medium"
    }

    fun getMediumPath(): String {
        return "$path/$STANDARD_MEDIUM.$extension";
    }
}