package me.dwidar.aflamy.shell.presentation.movie_details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.dwidar.aflamy.core.model.movies.MovieDetailsModel
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.configs.AflamyTheme
import me.dwidar.aflamy.shell.configs.cardRoundedCorner
import me.dwidar.aflamy.shell.configs.getGeneralHorizontalPadding
import me.dwidar.aflamy.shell.configs.getHeightUnit
import me.dwidar.aflamy.shell.configs.getWidthUnit
import me.dwidar.aflamy.shell.configs.hintColor
import me.dwidar.aflamy.shell.configs.screenHeight
import me.dwidar.aflamy.shell.configs.screenWidth
import me.dwidar.aflamy.shell.configs.spacerHeight
import me.dwidar.aflamy.shell.configs.white
import me.dwidar.aflamy.shell.presentation.common.PrimaryButton
import me.dwidar.aflamy.shell.presentation.main_screen.components.MovieCard
import me.dwidar.aflamy.shell.presentation.main_screen.components.MoviesCollection
import me.dwidar.aflamy.shell.presentation.movie_details.components.SimilarMoviesList

class MovieDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDetailsPage()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsPage() {
    // val state = viewModel.state.collectAsState()
    // viewModel.onIntent(MainScreenIntent.GetPopularMovies)

    val similar : MutableList<MovieModel> = mutableListOf()
    similar.add(
        MovieModel(
            title = "Novocaine",
            releaseDate = "2025-03-26",
            posterPath = "https://image.tmdb.org/t/p/w500/xUkUZ8eOnrOnnJAfusZUqKYZiDu.jpg"
        )
    )
    similar.add(
        MovieModel(
            title = "Novocaine",
            releaseDate = "2025-03-26",
            posterPath = "https://image.tmdb.org/t/p/w500/xUkUZ8eOnrOnnJAfusZUqKYZiDu.jpg"
        )
    )
    similar.add(
        MovieModel(
            title = "Novocaine",
            releaseDate = "2025-03-26",
            posterPath = "https://image.tmdb.org/t/p/w500/xUkUZ8eOnrOnnJAfusZUqKYZiDu.jpg"
        )
    )
    
    val movie = MovieDetailsModel(
        title = "Novocaine",
        releaseDate = "2025-03-26",
        posterPath = "https://image.tmdb.org/t/p/w780/xUkUZ8eOnrOnnJAfusZUqKYZiDu.jpg",
        overview = "When the girl of his dreams is kidnapped, everyman Nate turns his inability to feel pain into an unexpected strength in his fight to get her back.",
        tagline = "Nathan Caine can't feel pain.",
        revenue = 33561854.0,
        status = "Released"
    )

    AflamyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {  },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.statusBarsPadding()
                )
            },
            modifier = Modifier.fillMaxSize()) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeContentPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = getGeneralHorizontalPadding()),
                verticalArrangement = Arrangement.Top
            )
            {
                AsyncImage(
                    model = movie.posterPath,
                    contentDescription = "Example image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height((screenHeight() * 0.4).dp)
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = cardRoundedCorner,
                                topEnd = cardRoundedCorner
                            )
                        )
                )

                Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

                Text(movie.title, style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(getHeightUnit().dp))

                Text(movie.tagline, style = MaterialTheme.typography.labelMedium.copy(color = hintColor, fontStyle = FontStyle.Italic))

                Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

                Row (modifier = Modifier.fillMaxWidth()) {
                    
                    /// Release Date
                    Row {
                        Text("Release: ", style = MaterialTheme.typography.titleSmall.copy(color = hintColor))
                        Text(movie.releaseDate, style = MaterialTheme.typography.titleSmall)
                    }

                    Spacer(modifier = Modifier.width((getWidthUnit() * 6).dp))

                    /// Status
                    Row {
                        Text("Status: ", style = MaterialTheme.typography.titleSmall.copy(color = hintColor))
                        Text(movie.status, style = MaterialTheme.typography.titleSmall)
                    }
                }

                Spacer(modifier = Modifier.height(getHeightUnit().dp))

                /// Revenue
                Row {
                    Text("Revenue: ", style = MaterialTheme.typography.titleSmall.copy(color = hintColor))
                    Text("$${movie.revenue.toUInt()}", style = MaterialTheme.typography.titleSmall)
                }

                Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

                PrimaryButton(title = "Add To Watchlist", icon = Icons.Filled.Favorite) { }

                Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

                Text("Overview", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(getHeightUnit().dp))

                Text(movie.overview, style = MaterialTheme.typography.labelMedium.copy(color = hintColor))

                Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = hintColor
                )

                Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

                Text("Similar Movies", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

                SimilarMoviesList(movies = similar) { }

                Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = hintColor
                )

                Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

                Text("Cast & Crew", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    MovieDetailsPage()
}