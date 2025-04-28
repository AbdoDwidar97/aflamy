package me.dwidar.aflamy.core.presentation.movies_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.dwidar.aflamy.shell.repo.casts_repo.CastsRepoImpl
import me.dwidar.aflamy.shell.repo.movies_repo.MoviesRepoImpl

class MovieDetailsViewModel : ViewModel()
{
    private val _state = MutableStateFlow(MovieDetailsState())
    val state: StateFlow<MovieDetailsState> = _state

    private val moviesRepo = MoviesRepoImpl()
    private val castsRepo = CastsRepoImpl()

    fun onIntent(intent: MovieDetailsIntent) {
        when (intent) {
            is MovieDetailsIntent.OnGetCasts -> onGetCasts(movieId = intent.movieId)
            is MovieDetailsIntent.OnGetMovieDetails -> onGetMovieDetails(movieId = intent.movieId)
            is MovieDetailsIntent.OnGetSimilarMovies -> onGetSimilarMovies(movieId = intent.movieId)
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

                    _state.update {
                        it.copy(
                            numberOfRequests = _state.value.numberOfRequests - 1,
                            similarMovies = result.results.subList(0, 5)
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
                            casts = result.castList.subList(0, 5)
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
}