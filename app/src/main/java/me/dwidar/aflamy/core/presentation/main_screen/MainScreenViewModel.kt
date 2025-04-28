package me.dwidar.aflamy.core.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.repo.movies_repo.MoviesRepoImpl

class MainScreenViewModel : ViewModel()
{
    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state
    private val moviesRepo = MoviesRepoImpl()
    private var getMoviesSearchResultJob: Job? = null

    fun onIntent(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.OnGetPopularMovies -> onGetPopularMovies()
            is MainScreenIntent.OnGetMoviesWithSearch -> onGetMoviesSearchResult(query = intent.query)
        }
    }

    private fun onGetPopularMovies()
    {
        _state.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            moviesRepo.getPopularMovies()
                .onSuccess { result ->

                    val moviesGroupByYears = createMoviesGroupByYears(result.results)

                    _state.update {
                        it.copy(
                            isLoading = false,
                            moviesGroupByYears = moviesGroupByYears,
                            descendingYears = moviesGroupByYears.keys.toList().sortedDescending()
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }
        }
    }

    private fun onGetMoviesSearchResult(query: String)
    {
        getMoviesSearchResultJob?.cancel()

        _state.update {
            it.copy(isLoading = true, searchText = query)
        }

        getMoviesSearchResultJob = viewModelScope.launch {
            moviesRepo.getMoviesResult(query = query)
                .onSuccess { result ->

                    _state.update {
                        it.copy(
                            isLoading = false,
                            moviesSearch = result.results
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }
        }
    }

    private fun createMoviesGroupByYears(moviesList: MutableList<MovieModel>) : HashMap<Int, MutableList<MovieModel>>
    {
        val moviesGroup = HashMap<Int, MutableList<MovieModel>>()
        moviesList.forEach { model ->
            val year = model.releaseDate.split("-")[0].toInt()
            moviesGroup[year] = moviesGroup.getOrDefault(year, mutableListOf()).apply { add(model) }
        }

        return moviesGroup
    }
}