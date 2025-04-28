package me.dwidar.aflamy.shell.presentation.main_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.configs.getHeightUnit
import me.dwidar.aflamy.shell.configs.getWidthUnit

@Composable
fun MoviesGroupItemByYear(year: Int, movies: List<MovieModel>, onCardClick: (movieId: Int) -> Unit) {
    Column {
        Text(text = year.toString(), style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

        FlowRow(
            maxItemsInEachRow = 2,
            verticalArrangement = Arrangement.spacedBy(getHeightUnit().dp),
            horizontalArrangement = Arrangement.spacedBy((getWidthUnit() * 2).dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            repeat(movies.size) {idx ->
                MovieCard(movie = movies[idx], onCardClick = onCardClick)
            }
        }
    }
}