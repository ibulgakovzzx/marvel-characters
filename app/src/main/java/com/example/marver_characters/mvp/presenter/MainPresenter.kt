package com.example.marver_characters.mvp.presenter

import com.arellomobile.mvp.MvpPresenter
import com.example.marver_characters.mvp.view.MainView
import com.example.marver_characters.ui.navigation.MainNavigator
import ru.terrakok.cicerone.Navigator

class MainPresenter(private val mainNavigator: MainNavigator): MvpPresenter<MainView>() {

    fun launchFirstScreen() {
        mainNavigator.newRootCharacterList()
    }

    fun setNavigator(navigator: Navigator) {
        mainNavigator.setNavigator(navigator)
    }

    fun removeNavigator() {
        mainNavigator.removeNavigator()
    }

}