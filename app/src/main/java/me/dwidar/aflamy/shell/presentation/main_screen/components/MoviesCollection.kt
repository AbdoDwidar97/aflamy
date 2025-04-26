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
fun MoviesCollection(moviesGroup: HashMap<Int, MutableList<MovieModel>>, yearsList: List<Int>) {

    LazyColumn (modifier = Modifier.fillMaxSize()){
        items(count = yearsList.size) { idx ->
            MoviesGroupItemByYear(yearsList[idx], movies = moviesGroup[yearsList[idx]] ?: listOf())
            Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))
        }
    }
}
