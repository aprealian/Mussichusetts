package com.teknokrait.mussichusetts.data.repository

import com.teknokrait.mussichusetts.data.model.Track
import com.teknokrait.mussichusetts.data.remote.ApiInterface
import com.teknokrait.mussichusetts.persistance.dao.TrackDao
import com.teknokrait.mussichusetts.util.Constants
import com.teknokrait.mussichusetts.util.Utils
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
class TrackRepository @Inject constructor(private val apiInterface: ApiInterface,
                                          private val cryptoCurrencyDao: TrackDao, private val utils: Utils) {

    fun getTracks(limit: Int, offset: Int): Observable<List<Track>> {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<List<Track>>? = null
        if (hasConnection) {
            observableFromApi = getTracksFromApi()
        }
        val observableFromDb = getTracksFromDb(limit, offset)

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    private fun getTracksFromApi(): Observable<List<Track>> {
        return apiInterface.getTracks(Constants.START_ZERO_VALUE)
                .doOnNext {
                    Timber.e(it.size.toString())
                    for (item in it) {
                        cryptoCurrencyDao.insertTrack(item)
                    }
                }
    }

    private fun getTracksFromDb(limit: Int, offset: Int): Observable<List<Track>> {
        return cryptoCurrencyDao.queryTracks(limit, offset)
                .toObservable()
                .doOnNext {
                    Timber.e(it.size.toString())
                }
    }
}