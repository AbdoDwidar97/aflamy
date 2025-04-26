package me.dwidar.aflamy.core.presentation.main_screen

import me.dwidar.aflamy.core.model.movies.MovieModel

data class MainScreenState(
    val isLoading: Boolean = false,
    val moviesGroupByYears: HashMap<Int, List<MovieModel>> = HashMap<Int, List<MovieModel>>(),
    val descendingYears: List<Int> = mutableListOf()
)
