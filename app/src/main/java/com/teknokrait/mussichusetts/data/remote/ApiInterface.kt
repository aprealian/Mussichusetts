package com.teknokrait.mussichusetts.data.remote

import com.teknokrait.mussichusetts.data.model.Track
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
interface ApiInterface {

    @GET("ticker/")
    fun getTracks(@Query("start") start: String): Observable<List<Track>>
}