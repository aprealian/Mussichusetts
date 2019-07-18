package com.teknokrait.mussichusetts.data.model

import androidx.room.ColumnInfo
import io.realm.annotations.PrimaryKey
import androidx.room.Entity
import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */

@Entity(
        tableName = "tracks"
)
data class Track(

        @Json(name = "track_id")
        @PrimaryKey
        @ColumnInfo(name = "track_id")
        val id: String,

        @Json(name = "track_name")
        @ColumnInfo(name = "track_name")
        val name: String?,

        @Json(name = "album_id")
        @ColumnInfo(name = "album_id")
        val albumId: String,

        @Json(name = "album_name")
        @ColumnInfo(name = "album_name")
        val albumName: String?,

        @Json(name = "artist_id")
        @ColumnInfo(name = "artist_id")
        val artistId: String,

        @Json(name = "artist_name")
        @ColumnInfo(name = "artist_name")
        val artistName: String?
) : Serializable