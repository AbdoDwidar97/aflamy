package me.dwidar.aflamy.core.presentation.movies_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.dwidar.aflamy.core.model.casts.CastMemberModel
import me.dwidar.aflamy.core.model.casts.DepartmentType
import me.dwidar.aflamy.core.repo.movies_repo.MoviesRepo
import me.dwidar.aflamy.shell.repo.casts_repo.CastsRepoImpl
import me.dwidar.aflamy.shell.repo.movies_repo.MoviesRepoImpl

class MovieDetailsViewModel(private val moviesRepo: MoviesRepo = MoviesRepoImpl()) : ViewModel()
{
    private val _state = MutableStateFlow(MovieDetailsState())
    val state: StateFlow<MovieDetailsState> = _state
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

                    val actors = result.castList
                    val crew = result.crewList

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
}