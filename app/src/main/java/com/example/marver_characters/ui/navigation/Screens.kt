package com.example.marver_characters.ui.navigation

import androidx.fragment.app.Fragment
import com.example.marver_characters.data.model.Character
import com.example.marver_characters.ui.fragments.CharacterDetailFragment
import com.example.marver_characters.ui.fragments.CharactersListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    object CharacterList: SupportAppScreen() {
        override fun getFragment(): Fragment = CharactersListFragment()
        override fun getScreenKey(): String = CharactersListFragment::class.java.simpleName
    }

    class CharacterDetail(private val character: Character): SupportAppScreen() {
        override fun getFragment(): Fragment = CharacterDetailFragment.newInstance(character)
        override fun getScreenKey(): String = CharacterDetailFragment::class.java.simpleName
    }

}