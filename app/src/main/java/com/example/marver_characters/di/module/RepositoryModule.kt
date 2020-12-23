package com.example.marver_characters.di.module

import com.example.marver_characters.data.db.dao.CharacterDao
import com.example.marver_characters.data.network.Api
import com.example.marver_characters.data.network.util.AuthHelper
import com.example.marver_characters.data.repository.CharactersListRepository
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule {

    @Provides
    fun provideCharacterListRepository(api: Api, characterDao: CharacterDao): CharactersListRepository =
        CharactersListRepository(api, characterDao, AuthHelper())
}