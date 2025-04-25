package me.dwidar.aflamy.shell.presentation.main_screen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.configs.getHeightUnit

@Composable
fun MoviesCollection() {
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

    LazyColumn (modifier = Modifier.fillMaxSize()){
        items(count = myYears.size) { idx ->
            MoviesGroupItemByYear(myYears[idx], movies = myItems)
            Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))
        }
    }
}
