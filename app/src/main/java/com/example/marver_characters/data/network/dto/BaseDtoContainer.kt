package com.example.marver_characters.data.network.dto

class BaseDtoContainer<T>
constructor(val offset: Int,
            val limit: Int,
            val total: Int,
            val count: Int,
            val results: List<T>)