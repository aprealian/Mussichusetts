package com.teknokrait.mussichusetts.persistance.local

import androidx.room.RoomDatabase
import com.teknokrait.mussichusetts.persistance.dao.TrackDao

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
//@Database(entities = [(CryptoCurrency::class)], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun tracksDao(): TrackDao
}