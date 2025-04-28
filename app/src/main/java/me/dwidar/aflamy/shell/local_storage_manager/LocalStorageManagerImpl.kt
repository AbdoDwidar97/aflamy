package me.dwidar.aflamy.shell.local_storage_manager

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import me.dwidar.aflamy.core.local_storage_manager.LocalStorageManager
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.local_storage_manager.models.RealmMovieModel

class LocalStorageManagerImpl: LocalStorageManager
{
    private companion object {
        lateinit var realm: Realm
    }

    override fun init() {
        val config = RealmConfiguration.create(
            schema = setOf(RealmMovieModel::class)
        )
        realm = Realm.open(config)
    }

    override suspend fun addMovie(movie: MovieModel)
    {
        realm.write {

            val movieEntity = RealmMovieModel().apply {
                adult = movie.adult
                backdropPath = movie.backdropPath
                id = movie.id
                originalLanguage = movie.originalLanguage
                originalTitle = movie.originalTitle
                overview = movie.overview
                popularity = movie.popularity
                posterPath = movie.posterPath
                releaseDate = movie.releaseDate
                title = movie.title
                video = movie.video
                voteAverage = movie.voteAverage
                voteCount = movie.voteCount
            }

            copyToRealm(movieEntity)
        }
    }

    override suspend fun readAllMovies(): List<MovieModel> {
        val movies = realm.query(RealmMovieModel::class).find()

        val moviesList : MutableList<MovieModel> = mutableListOf()

        movies.forEach { realmMovie ->
            moviesList.add(
                MovieModel(
                    adult = realmMovie.adult,
                    backdropPath = realmMovie.backdropPath,
                    id = realmMovie.id,
                    originalLanguage = realmMovie.originalLanguage,
                    originalTitle = realmMovie.originalTitle,
                    overview = realmMovie.overview,
                    popularity = realmMovie.popularity,
                    posterPath = realmMovie.posterPath,
                    releaseDate = realmMovie.releaseDate,
                    title = realmMovie.title,
                    video = realmMovie.video,
                    voteAverage = realmMovie.voteAverage,
                    voteCount = realmMovie.voteCount,
                )
            )
        }

        return moviesList
    }

    override suspend fun close() {
        realm.close()
    }

}