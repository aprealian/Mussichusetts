package com.teknokrait.mussichusetts.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teknokrait.mussichusetts.data.model.Track
import io.reactivex.Single

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
@Dao
interface TrackDao {

    @Query("SELECT * FROM tracks ORDER BY track_id limit :limit offset :offset")
    fun queryTracks(limit: Int, offset: Int): Single<List<Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: Track)

    @Insert(
            onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAllTracks(track: List<Track>)
}