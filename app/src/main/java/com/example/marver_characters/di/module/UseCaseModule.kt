package com.example.marver_characters.di.module

import com.example.marver_characters.application.usecase.CharactersListInteractor
import com.example.marver_characters.data.repository.CharactersListRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideCharacterInteractor(charactersListRepository: CharactersListRepository): CharactersListInteractor =
        CharactersListInteractor(charactersListRepository)

}