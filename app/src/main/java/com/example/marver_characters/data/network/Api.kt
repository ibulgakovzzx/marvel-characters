package com.example.marver_characters.data.network

import com.example.marver_characters.data.network.dto.BaseDto
import com.example.marver_characters.data.network.dto.CharacterDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("/v1/public/characters")
    fun getCharactersList(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") timestam: String,
        @Query("limit") limit: Int,
        @Query("name") name: String): Single<BaseDto<CharacterDto>>
}