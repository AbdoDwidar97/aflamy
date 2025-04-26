package me.dwidar.aflamy.core.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun onIntent(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.GetPopularMovies -> onGetPopularMovies()
            is MainScreenIntent.GoDetailsScreen -> {

            }
            is MainScreenIntent.GetMoviesWithSearch -> {

            }
        }
    }

    private fun onGetPopularMovies()
    {
        /*// Sample data
        val myYears = listOf(2025, 2024, 2023)

        val myItems : MutableList<MovieModel> = mutableListOf()

        myItems.add(MovieModel(
            title = "Movie 1",
            releaseDate = "2025-03-26",
            posterPath = "https://image.tmdb.org/t/p/w500/xUkUZ8eOnrOnnJAfusZUqKYZiDu.jpg"
        ))

        myItems.add(MovieModel(
            title = "Movie 2 Movie 2 Movie 2 Movies 2222",
            releaseDate = "2025-03-26",
            posterPath = "https://image.tmdb.org/t/p/w500/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg"
        ))

        myItems.add(MovieModel(
            title = "Movie 3",
            releaseDate = "2025-03-26",
            posterPath = "https://image.tmdb.org/t/p/w500/t6HJH3gXtUqVinyFKWi7Bjh73TM.jpg"
        ))

        val myData = HashMap<Int, List<MovieModel>>()
        for (idx in myYears.indices){
            myData[myYears[idx]] = myItems
        }*/

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
                    Log.e("5555555555555555555", "onGetPopularMovies: ${error.message}")
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