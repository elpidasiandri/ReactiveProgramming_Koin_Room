package com.example.myapplication.utils.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Flowables
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.scheduleIOUI(): Observable<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.scheduleIOUI(): Single<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun Completable.scheduleIOUI(): Completable = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.retryWithDelay(retryCount: Int, delayInMillis: Long): Observable<T> =
    retryWhen { errors ->
        Observables.zip(
            errors,
            Observable.interval(delayInMillis, TimeUnit.MILLISECONDS)
        )
            .map { if (it.second >= retryCount) throw it.first }
    }

fun <T> Single<T>.retryWithDelay(retryCount: Int, delayInMillis: Long): Single<T> =
    retryWhen { errors ->
        Flowables.zip(
            errors,
            Flowable.interval(delayInMillis, TimeUnit.MILLISECONDS)
        )
            .map { if (it.second >= retryCount) throw it.first }
    }

fun <T>subscribeToStateG(state: StateFlow<T>, viewLifecycleOwner: LifecycleOwner, actOnEvents: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            state.collect {
                actOnEvents(it)
            }
        }
    }
}