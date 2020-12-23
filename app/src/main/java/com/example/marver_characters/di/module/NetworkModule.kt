package com.example.marver_characters.di.module

import android.content.Context
import com.example.marver_characters.App
import com.example.marver_characters.data.network.Api
import com.example.marver_characters.data.network.ApiFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/"
    }

    @Provides
    fun provideContext(app: App): Context =
        app.applicationContext

    @Singleton
    @Provides
    fun provideApi(context: Context): Api {
        val factory = ApiFactory()
        return factory.create(context, BASE_URL)
    }

}