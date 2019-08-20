package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.movie.MovieDetail
import com.example.data.entities.tv.TvShowDetail

/**
 * Main database description.
 */
@Database(
    entities = [
        MovieDetail::class,
        TvShowDetail::class],
    version = 3,
    exportSchema = false
)
abstract class SearchDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao

}
