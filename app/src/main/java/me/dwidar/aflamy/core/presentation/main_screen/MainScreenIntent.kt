package me.dwidar.aflamy.core.presentation.main_screen

sealed class MainScreenIntent
{
    object OnGetPopularMovies : MainScreenIntent()
    object OnGetNowPlayingMovies : MainScreenIntent()
    data class OnGetMoviesWithSearch(val query: String) : MainScreenIntent()
}