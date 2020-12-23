package com.example.marver_characters.di.module

import com.example.marver_characters.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}