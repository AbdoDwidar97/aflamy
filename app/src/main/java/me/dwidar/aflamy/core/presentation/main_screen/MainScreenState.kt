package me.dwidar.aflamy.core.presentation.main_screen

import me.dwidar.aflamy.core.model.movies.MovieModel

data class MainScreenState(
    val isLoading: Boolean = false,
    val moviesGroupByYears: HashMap<Int, MutableList<MovieModel>> = HashMap<Int, MutableList<MovieModel>>(),
    val moviesSearch: List<MovieModel> = mutableListOf(),
    val searchText: String = "",
    val descendingYears: List<Int> = mutableListOf()
)
