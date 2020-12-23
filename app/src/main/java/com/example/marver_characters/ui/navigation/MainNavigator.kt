package com.example.marver_characters.ui.navigation

import com.example.marver_characters.data.model.Character
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router

class MainNavigator
constructor(private val cicerone: Cicerone<Router>) {

    fun setNavigator(navigator: Navigator) =
        cicerone.navigatorHolder.setNavigator(navigator)

    fun removeNavigator() =
        cicerone.navigatorHolder.removeNavigator()

    fun exit() = cicerone.router.exit()

    fun newRootCharacterList() {
        cicerone.router.newRootScreen(Screens.CharacterList)
    }

    fun navigateToCharacter(character: Character) {
        cicerone.router.navigateTo(Screens.CharacterDetail(character))
    }
}