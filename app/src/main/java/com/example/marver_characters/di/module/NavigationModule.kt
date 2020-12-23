package com.example.marver_characters.di.module

import com.example.marver_characters.ui.navigation.MainNavigator
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideMainNavigator(): MainNavigator = MainNavigator(cicerone)

}
