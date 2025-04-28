package me.dwidar.aflamy.shell.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.configs.getHeightUnit
import me.dwidar.aflamy.shell.configs.getWidthUnit
import me.dwidar.aflamy.shell.presentation.main_screen.components.MovieCard

@Composable
fun NormalMoviesList(movies: List<MovieModel>, onCardClick: (movieId: Int) -> Unit)
{
    if (movies.isEmpty())
        Text("No Result", style = MaterialTheme.typography.titleMedium)
    else {
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
