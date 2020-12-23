package com.example.marver_characters.di.module

import com.example.marver_characters.ui.fragments.CharacterDetailFragment
import com.example.marver_characters.ui.fragments.CharactersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector
    fun bindCharactersListFragment(): CharactersListFragment

    @ContributesAndroidInjector
    fun bindCharactersFragment(): CharacterDetailFragment
}