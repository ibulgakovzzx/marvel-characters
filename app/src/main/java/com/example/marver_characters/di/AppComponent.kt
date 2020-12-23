package com.example.marver_characters.di

import com.example.marver_characters.App
import com.example.marver_characters.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    RepositoryModule::class,
    PresenterModule::class,
    UseCaseModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    DbModule::class,
    NetworkModule::class,
    NavigationModule::class
])
interface ApplicationComponent: AndroidInjector<App> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}