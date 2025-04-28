package me.dwidar.aflamy.core.local_storage_manager

import me.dwidar.aflamy.core.model.movies.MovieModel

interface LocalStorageManager
{
    fun init()
    suspend fun addMovie(movie: MovieModel)
    suspend fun readAllMovies(): List<MovieModel>
    suspend fun close()
}