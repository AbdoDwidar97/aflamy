package me.dwidar.aflamy.shell.presentation.movie_details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.dwidar.aflamy.core.model.movies.MovieDetailsModel
import me.dwidar.aflamy.core.model.movies.MovieModel
import me.dwidar.aflamy.shell.configs.AflamyTheme
import me.dwidar.aflamy.shell.configs.cardRoundedCorner
import me.dwidar.aflamy.shell.configs.getGeneralHorizontalPadding
import me.dwidar.aflamy.shell.configs.screenHeight

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

    val movie = MovieDetailsModel(
        title = "Novocaine",
        releaseDate = "2025-03-26",
        posterPath = "https://image.tmdb.org/t/p/w780/xUkUZ8eOnrOnnJAfusZUqKYZiDu.jpg",
        overview = "When the girl of his dreams is kidnapped, everyman Nate turns his inability to feel pain into an unexpected strength in his fight to get her back.",
        tagline = "Nathan Caine can't feel pain.",
        revenue = 33561854.0,
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
                        .clip(RoundedCornerShape(topStart = cardRoundedCorner, topEnd = cardRoundedCorner))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    MovieDetailsPage()
}