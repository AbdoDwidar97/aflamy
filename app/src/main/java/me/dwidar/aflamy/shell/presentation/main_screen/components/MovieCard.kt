package me.dwidar.aflamy.shell.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.configs.cardBackgroundColor
import me.dwidar.aflamy.shell.configs.cardRoundedCorner
import me.dwidar.aflamy.shell.configs.getGeneralHorizontalPadding
import me.dwidar.aflamy.shell.configs.getHeightUnit
import me.dwidar.aflamy.shell.configs.screenHeight
import me.dwidar.aflamy.shell.configs.screenWidth

@Composable
fun MovieCard(movie: MovieModel, onCardClick: (movieId: Int) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(cardRoundedCorner))
            .height((screenHeight() * 0.3).dp)
            .width((screenWidth() * 0.45).dp)
            .background(cardBackgroundColor)
            .clickable {
                onCardClick(movie.id)
            }
    ) {
        Column (verticalArrangement = Arrangement.SpaceBetween){
            AsyncImage(
                model = movie.posterPath,
                contentDescription = "Example image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height((screenHeight() * 0.23).dp)
                    .clip(RoundedCornerShape(topStart = cardRoundedCorner, topEnd = cardRoundedCorner))
            )

            Column (modifier = Modifier.padding(horizontal = getGeneralHorizontalPadding())){
                Spacer(modifier = Modifier.height(getHeightUnit().dp))

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(getHeightUnit().dp))

                Text(
                    text = movie.releaseDate.split("-")[0],
                    style = MaterialTheme.typography.labelSmall,
                )
            }

        }
    }
}