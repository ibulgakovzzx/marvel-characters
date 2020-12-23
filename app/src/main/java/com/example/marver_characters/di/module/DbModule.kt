package com.example.marver_characters.di.module

import android.content.Context
import androidx.room.Room
import com.example.marver_characters.data.db.AppDatabase
import com.example.marver_characters.data.db.dao.CharacterDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .build()

    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao =
            appDatabase.characterDao()
}