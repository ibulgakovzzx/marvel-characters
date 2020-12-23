package com.example.marver_characters.data.network

import android.content.Context
import com.cookiedev.move.data.network.util.ConnectionManager
import com.example.marver_characters.data.network.interceptor.ResponseInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiFactory {
    fun create(context: Context, baseUrl: String): Api {
        val connectionManager: ConnectionManager = createConnectionManager(context)
        val gson: Gson = createGson()

        val responseInterceptor: ResponseInterceptor = createResponseInterceptor(connectionManager)

        val okHttpClient: OkHttpClient = createOkHttpClient(responseInterceptor)
        return createApi(okHttpClient, gson, baseUrl)
    }

    private fun createResponseInterceptor(connectionManager: ConnectionManager): ResponseInterceptor =
        ResponseInterceptor(connectionManager)

    private fun createOkHttpClient(responseInterceptor: ResponseInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(responseInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    private fun createGson(): Gson = GsonBuilder().setLenient().create()

    private fun createApi(okHttpClient: OkHttpClient, gson: Gson, baseUrl: String): Api =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Api::class.java)

    private fun createConnectionManager(context: Context): ConnectionManager = ConnectionManager(context)
}