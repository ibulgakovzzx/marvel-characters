package com.example.marver_characters.mvp.view

import com.arellomobile.mvp.MvpView
import com.example.marver_characters.data.model.Character

interface CharactersListView: MvpView {
    fun setList(list: List<Character>)
    fun showProgress()
    fun hideProgress()
    fun showNetworkError()
}