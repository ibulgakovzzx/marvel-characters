package com.example.marver_characters.di.module

import com.example.marver_characters.application.usecase.CharactersListInteractor
import com.example.marver_characters.mvp.presenter.CharactersListPresenter
import com.example.marver_characters.mvp.presenter.MainPresenter
import com.example.marver_characters.ui.navigation.MainNavigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideMainPresenter(mainNavigator: MainNavigator): MainPresenter =
        MainPresenter(mainNavigator)

    @Provides
    @Singleton
    fun provideCharacterListPresenter(charactersListInteractor: CharactersListInteractor,
                                      mainNavigator: MainNavigator): CharactersListPresenter =
        CharactersListPresenter(charactersListInteractor, mainNavigator)
}