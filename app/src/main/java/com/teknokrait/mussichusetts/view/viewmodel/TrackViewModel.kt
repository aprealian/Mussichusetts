package com.teknokrait.mussichusetts.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import com.teknokrait.mussichusetts.data.model.Track
import com.teknokrait.mussichusetts.data.repository.TrackRepository
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import java.util.concurrent.TimeUnit.MILLISECONDS

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
class TrackViewModel @Inject constructor(
        private val trackRepository: TrackRepository) : ViewModel() {

    var tracksResult: MutableLiveData<List<Track>> = MutableLiveData()
    var tracksError: MutableLiveData<String> = MutableLiveData()
    var tracksLoader: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var disposableObserver: DisposableObserver<List<Track>>

    fun tracksResult(): LiveData<List<Track>> {
        return tracksResult
    }

    fun tracksError(): LiveData<String> {
        return tracksError
    }

    fun tracksLoader(): LiveData<Boolean> {
        return tracksLoader
    }

    fun loadTracks(limit: Int, offset: Int) {

        disposableObserver = object : DisposableObserver<List<Track>>() {
            override fun onComplete() {

            }

            override fun onNext(tracks: List<Track>) {
                tracksResult.postValue(tracks)
                tracksLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                tracksError.postValue(e.message)
                tracksLoader.postValue(false)
            }
        }

        trackRepository.getTracks(limit, offset)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, MILLISECONDS)
                .subscribe(disposableObserver)
    }

    fun disposeElements() {
        if (!disposableObserver.isDisposed) disposableObserver.dispose()
    }

}