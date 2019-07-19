package com.teknokrait.mussichusetts.persistance.local

import androidx.room.RoomDatabase
import com.teknokrait.mussichusetts.persistance.dao.TrackDao
import com.teknokrait.mussichusetts.data.model.Track;

//import android.arch.persistence.room.Database
//import android.arch.persistence.room.RoomDatabase

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
@androidx.room.Database(entities = [(Track::class)], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun tracksDao(): TrackDao
}