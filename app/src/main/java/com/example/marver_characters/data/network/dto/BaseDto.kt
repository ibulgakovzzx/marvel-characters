package com.example.marver_characters.data.network.dto

class BaseDto<T>(val code: Int, val status: String, val data: BaseDtoContainer<T>)