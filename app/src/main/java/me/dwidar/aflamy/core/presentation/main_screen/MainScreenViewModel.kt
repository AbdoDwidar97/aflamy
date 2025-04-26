package me.dwidar.aflamy.core.presentation.main_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.dwidar.aflamy.core.model.movies.MovieModel

class MainScreenViewModel : ViewModel()
{
    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state

    fun onIntent(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.GetPopularMovies -> {
                onGetPopularMovies()
            }
            is MainScreenIntent.GoDetailsScreen -> {

            }
            is MainScreenIntent.GetMoviesWithSearch -> {

            }
        }
    }

    private fun onGetPopularMovies()
    {
        // Sample data
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
        }

        _state.update {
            it.copy(moviesGroupByYears = myData, descendingYears = myYears)
        }
    }
}