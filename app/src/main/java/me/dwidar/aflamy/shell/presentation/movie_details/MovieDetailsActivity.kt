package me.dwidar.aflamy.shell.presentation.movie_details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import me.dwidar.aflamy.core.model.casts.CastMemberModel
import me.dwidar.aflamy.core.model.movies.MovieDetailsModel
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.core.presentation.movies_details.MovieDetailsIntent
import me.dwidar.aflamy.core.presentation.movies_details.MovieDetailsViewModel
import me.dwidar.aflamy.shell.configs.AflamyTheme
import me.dwidar.aflamy.shell.configs.cardRoundedCorner
import me.dwidar.aflamy.shell.configs.getGeneralHorizontalPadding
import me.dwidar.aflamy.shell.configs.getHeightUnit
import me.dwidar.aflamy.shell.configs.getWidthUnit
import me.dwidar.aflamy.shell.configs.hintColor
import me.dwidar.aflamy.shell.configs.screenHeight
import me.dwidar.aflamy.shell.presentation.common.ParagraphLabel
import me.dwidar.aflamy.shell.presentation.common.PrimaryButton
import me.dwidar.aflamy.shell.presentation.common.RowLabel
import me.dwidar.aflamy.shell.presentation.movie_details.components.CastCard
import me.dwidar.aflamy.shell.presentation.common.NormalMoviesList

@AndroidEntryPoint
class MovieDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val movieId = intent.getIntExtra("movie_id", -1)

        setContent {
            MovieDetailsPage(movieId = movieId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsPage(viewModel: MovieDetailsViewModel = hiltViewModel(), movieId: Int = -1) {
    val state = viewModel.state.collectAsState()

    viewModel.onIntent(MovieDetailsIntent.OnGetMovieDetails(movieId = movieId))
    viewModel.onIntent(MovieDetailsIntent.OnGetSimilarMovies(movieId = movieId))
    viewModel.onIntent(MovieDetailsIntent.OnGetCasts(movieId = movieId))

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
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeContentPadding()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = getGeneralHorizontalPadding()),
                    verticalArrangement = Arrangement.Top
                )
                {
                    MovieDetailsInfoSection(movie = state.value.movieDetailsModel)

                    Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = hintColor
                    )

                    Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

                    SimilarMoviesSection(similar = state.value.similarMovies)

                    Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = hintColor
                    )

                    Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

                    CastsSection(directors = state.value.directorsCast, actors = state.value.actorsCast)

                    Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))
                }

                if (state.value.numberOfRequests > 0){
                    Column (modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator()
                    }
                }
            }

        }
    }
}

@Composable
fun MovieDetailsInfoSection(movie: MovieDetailsModel)
{
    Column {
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

            RowLabel(title = "Release: ", value = movie.releaseDate)

            Spacer(modifier = Modifier.width((getWidthUnit() * 6).dp))

            RowLabel(title = "Status: ", value = movie.status)
        }

        Spacer(modifier = Modifier.height(getHeightUnit().dp))

        RowLabel(title = "Revenue: ", value = "$${movie.revenue.toUInt()}")

        Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

        PrimaryButton(title = "Add To Watchlist", icon = Icons.Filled.Favorite) { }

        Spacer(modifier = Modifier.height((getHeightUnit() * 3).dp))

        ParagraphLabel(title = "Overview", content = movie.overview)

    }
}

@Composable
fun SimilarMoviesSection(similar: List<MovieModel>)
{
    Column {
        Text("Similar Movies", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

        NormalMoviesList(movies = similar) { }
    }
}

@Composable
fun CastsSection(directors: List<CastMemberModel>, actors: List<CastMemberModel>)
{
    Column {
        Text("Cast & Crew", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

        Text("Top Directors", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

        CastList(casts = directors)

        Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

        Text("Top Actors", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height((getHeightUnit() * 2).dp))

        CastList(casts = actors)
    }
}

@Composable
fun CastList(casts: List<CastMemberModel>)
{
    FlowRow(
        maxItemsInEachRow = 2,
        verticalArrangement = Arrangement.spacedBy(getHeightUnit().dp),
        horizontalArrangement = Arrangement.spacedBy((getWidthUnit() * 2).dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        repeat(casts.size) {idx ->
            CastCard(casts[idx])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    MovieDetailsPage()
}