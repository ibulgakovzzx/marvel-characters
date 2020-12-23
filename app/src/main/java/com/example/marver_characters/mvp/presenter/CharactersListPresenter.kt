package com.example.marver_characters.mvp.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.marver_characters.mvp.common.BasePresenter
import com.example.marver_characters.application.usecase.CharactersListInteractor
import com.example.marver_characters.data.model.Character
import com.example.marver_characters.data.network.NetworkConnectionException
import com.example.marver_characters.mvp.view.CharactersListView
import com.example.marver_characters.ui.navigation.MainNavigator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@InjectViewState
class CharactersListPresenter
constructor(private val charactersListInteractor: CharactersListInteractor,
            private val mainNavigator: MainNavigator): BasePresenter<CharactersListView>() {

    override fun onFirstViewAttach() {
        viewState.showProgress()
        compositeDisposable.add(
                charactersListInteractor.getCharactersList("")
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally { viewState.hideProgress() }
                        .subscribe({
                            viewState.setList(it)
                        }, {
                            if(it is NetworkConnectionException) {
                                viewState.showNetworkError()
                            }
                        }))
    }

    fun startObserveQuery(observable: Observable<CharSequence>) {
        compositeDisposable.add(observable
            .doOnNext { viewState.showProgress() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter {
                if(it.isEmpty()) {
                    viewState.hideProgress()
                    viewState.setList(listOf())
                    false
                } else {
                    true
                }
            }
            .flatMapSingle { charactersListInteractor.getCharactersList(it.toString()) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.hideProgress()
                viewState.setList(it)
            }, {
                viewState.hideProgress()
                startObserveQuery(observable)
            })
        )
    }

    fun openCharactersDetail(character: Character) {
        compositeDisposable.add(
                charactersListInteractor.saveCharacter(character)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            mainNavigator.navigateToCharacter(character)
                        }, Throwable::printStackTrace))
    }
}