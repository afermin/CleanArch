package com.example.data.db

import android.util.SparseIntArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.movie.Movie
import com.example.data.entities.movie.MovieDetail
import java.util.*

/**
 * Created by Alexander Fermin (alexfer06@gmail.com) on 2019-08-20.
 */
@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg movie: Movie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertMovie(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun createMovieIfNotExists(movie: Movie): Long

    @Query("SELECT * FROM movie WHERE id = :id")
    abstract fun load(id: Int): LiveData<Movie>

    @Query("SELECT * FROM movie_detail WHERE id = :id")
    abstract fun loadDetail(id: Int): LiveData<MovieDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDetail(vararg movie: MovieDetail)

    @Query(
        """
        SELECT * FROM Movie
        WHERE id = :type
        ORDER BY releaseDate DESC"""
    )
    abstract fun loadMovies(type: String): LiveData<List<Movie>>


    fun loadOrdered(movieIds: List<Int>): LiveData<List<Movie>> {
        val order = SparseIntArray()
        movieIds.withIndex().forEach {
            order.put(it.value, it.index)
        }
        return Transformations.map(loadById(movieIds)) { movie ->
            Collections.sort(movie) { r1, r2 ->
                val pos1 = order.get(r1.id)
                val pos2 = order.get(r2.id)
                pos1 - pos2
            }
            movie
        }
    }

    @Query("SELECT * FROM Movie WHERE id in (:movieIds)")
    protected abstract fun loadById(movieIds: List<Int>): LiveData<List<Movie>>
}