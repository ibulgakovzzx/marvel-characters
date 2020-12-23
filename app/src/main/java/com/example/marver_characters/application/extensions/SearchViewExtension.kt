package com.example.marver_characters.application.extensions


import androidx.annotation.CheckResult
import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

/**
 * Copy from https://github.com/JakeWharton/RxBinding/blob/78f7ebdeb9dcf373abb9fc43bde838cfe2fef62a/rxbinding/src/main/java/com/jakewharton/rxbinding4/internal/mainThread.kt
 * and simplified
 */
@CheckResult
fun SearchView.queryTextChanges(): InitialValueObservable<CharSequence> {
    return SearchViewQueryTextChangesObservable(this)
}

private class SearchViewQueryTextChangesObservable(
    private val view: SearchView
) : InitialValueObservable<CharSequence>() {

    private class Listener(
        private val searchView: SearchView,
        private val observer: Observer<in CharSequence>
    ) : MainThreadDisposable(), SearchView.OnQueryTextListener {

        override fun onQueryTextChange(s: String): Boolean {
            if (!isDisposed) {
                observer.onNext(s)
                return true
            }
            return false
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }

        override fun onDispose() {
            searchView.setOnQueryTextListener(null)
        }
    }

    override val initialValue: CharSequence get() = view.query

    override fun subscribeListener(observer: Observer<in CharSequence>) {
        val listener = Listener(view, observer)
        observer.onSubscribe(listener)
        view.setOnQueryTextListener(listener)
    }
}

abstract class InitialValueObservable<T> : Observable<T>() {
    protected abstract val initialValue: T

    override fun subscribeActual(observer: Observer<in T>) {
        subscribeListener(observer)
        observer.onNext(initialValue)
    }

    protected abstract fun subscribeListener(observer: Observer<in T>)

    fun skipInitialValue(): Observable<T> = Skipped()

    private inner class Skipped : Observable<T>() {
        override fun subscribeActual(observer: Observer<in T>) {
            subscribeListener(observer)
        }
    }
}
