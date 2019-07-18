package com.teknokrait.mussichusetts.di.module

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.teknokrait.mussichusetts.persistance.dao.TrackDao
import com.teknokrait.mussichusetts.persistance.local.Database
import com.teknokrait.mussichusetts.util.Constants
import com.teknokrait.mussichusetts.util.Utils
import com.teknokrait.mussichusetts.view.viewmodel.TrackViewModelFactory
import dagger.Provides
import javax.inject.Singleton
import dagger.Module

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
@Module
class AppModule(private val app: Application) {
    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE tracks RENAME TO tracksNew")
            }
        }
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideTracksDatabase(app: Application): Database = Room.databaseBuilder(app,
            Database::class.java, Constants.DATABASE_NAME)
            /*.addMigrations(MIGRATION_1_2)*/
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideTracksDao(
            database: Database): TrackDao = database.tracksDao()

    @Provides
    @Singleton
    fun provideTracksViewModelFactory(
            factory: TrackViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}