package me.dwidar.aflamy.core.presentation.main_screen

sealed class MainScreenIntent
{
    object GetPopularMovies : MainScreenIntent()
    object GoDetailsScreen : MainScreenIntent()
    data class GetMoviesWithSearch(val query: String) : MainScreenIntent()
}