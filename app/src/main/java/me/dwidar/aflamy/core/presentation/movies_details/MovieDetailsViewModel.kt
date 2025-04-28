package me.dwidar.aflamy.core.presentation.movies_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.dwidar.aflamy.core.model.casts.CastMemberModel
import me.dwidar.aflamy.core.model.casts.DepartmentType
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.local_storage_manager.LocalStorageManagerImpl
import me.dwidar.aflamy.shell.repo.casts_repo.CastsRepoImpl
import me.dwidar.aflamy.shell.repo.movies_repo.MoviesRepoImpl

class MovieDetailsViewModel : ViewModel()
{
    private val _state = MutableStateFlow(MovieDetailsState())
    val state: StateFlow<MovieDetailsState> = _state

    private val moviesRepo = MoviesRepoImpl()
    private val castsRepo = CastsRepoImpl()
    private val localStorageManager = LocalStorageManagerImpl()

    fun onIntent(intent: MovieDetailsIntent) {
        when (intent) {
            is MovieDetailsIntent.OnGetCasts -> onGetCasts(movieId = intent.movieId)
            is MovieDetailsIntent.OnGetMovieDetails -> onGetMovieDetails(movieId = intent.movieId)
            is MovieDetailsIntent.OnGetSimilarMovies -> onGetSimilarMovies(movieId = intent.movieId)
            is MovieDetailsIntent.OnAddMovieToWatchlist -> onAddMovieToWatchlist()
        }
    }

    private fun onGetMovieDetails(movieId: Int)
    {
        _state.update {
            it.copy(numberOfRequests = _state.value.numberOfRequests + 1)
        }

        viewModelScope.launch {
            moviesRepo.getMovieDetails(movieId = movieId)
                .onSuccess { result ->

                    _state.update {
                        it.copy(
                            numberOfRequests = _state.value.numberOfRequests - 1,
                            movieDetailsModel = result
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(numberOfRequests = _state.value.numberOfRequests - 1)
                    }
                }
        }
    }

    private fun onGetSimilarMovies(movieId: Int)
    {
        _state.update {
            it.copy(numberOfRequests = _state.value.numberOfRequests + 1)
        }

        viewModelScope.launch {
            moviesRepo.getSimilarMovies(movieId = movieId)
                .onSuccess { result ->

                    val maxIdx = result.results.size.coerceAtMost(5)
                    _state.update {
                        it.copy(
                            numberOfRequests = _state.value.numberOfRequests - 1,
                            similarMovies = result.results.subList(0, maxIdx)
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(numberOfRequests = _state.value.numberOfRequests - 1)
                    }
                }
        }
    }

    private fun onGetCasts(movieId: Int)
    {
        _state.update {
            it.copy(numberOfRequests = _state.value.numberOfRequests + 1)
        }

        viewModelScope.launch {
            castsRepo.getCasts(movieId = movieId)
                .onSuccess { result ->

                    _state.update {
                        it.copy(
                            numberOfRequests = _state.value.numberOfRequests - 1,
                            actorsCast = getCastsWithDescendingPopularity(casts = result.castList as MutableList<CastMemberModel>, departmentType = DepartmentType.Acting),
                            directorsCast = getCastsWithDescendingPopularity(casts = result.crewList as MutableList<CastMemberModel>, departmentType = DepartmentType.Directing)
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(numberOfRequests = _state.value.numberOfRequests - 1)
                    }
                }
        }
    }

    private fun getCastsWithDescendingPopularity(casts: MutableList<CastMemberModel>, departmentType: DepartmentType): List<CastMemberModel>
    {
        val orderedCastList : MutableList<CastMemberModel> = mutableListOf()

        casts.sortByDescending { cast ->  cast.popularity }

        casts.forEach { cast ->
            if (cast.knownForDepartment == departmentType)
                orderedCastList.add(cast)
            if (orderedCastList.size == 5) return orderedCastList
        }

        return orderedCastList
    }

    private fun onAddMovieToWatchlist()
    {
        _state.update {
            it.copy(numberOfRequests = _state.value.numberOfRequests + 1)
        }

        viewModelScope.launch {
            val movie = MovieModel(
                adult = _state.value.movieDetailsModel.adult,
                backdropPath = _state.value.movieDetailsModel.backdropPath,
                id = _state.value.movieDetailsModel.id,
                originalLanguage = _state.value.movieDetailsModel.originalLanguage,
                originalTitle = _state.value.movieDetailsModel.originalTitle,
                overview = _state.value.movieDetailsModel.overview,
                popularity = _state.value.movieDetailsModel.popularity,
                posterPath = _state.value.movieDetailsModel.posterPath,
                releaseDate = _state.value.movieDetailsModel.releaseDate,
                title = _state.value.movieDetailsModel.title,
                video = _state.value.movieDetailsModel.video,
                voteAverage = _state.value.movieDetailsModel.voteAverage,
                voteCount = _state.value.movieDetailsModel.voteCount,
            )

            localStorageManager.addMovie(movie = movie)

            runBlocking {
                val watchlistMap = _state.value.watchlist
                watchlistMap[movie.id] = movie
                _state.update {
                    it.copy(numberOfRequests = _state.value.numberOfRequests - 1, watchlist = watchlistMap)
                }
            }

        }
    }
}