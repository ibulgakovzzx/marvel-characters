package com.example.marver_characters.mvp.common

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V: MvpView>: MvpPresenter<V>() {
    val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()
    }
}