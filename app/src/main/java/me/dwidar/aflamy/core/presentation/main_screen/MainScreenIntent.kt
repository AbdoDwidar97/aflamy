package me.dwidar.aflamy.core.presentation.main_screen

sealed class MainScreenIntent
{
    object GetPopularMovies : MainScreenIntent()
    data class GetMoviesWithSearch(val query: String) : MainScreenIntent()
}