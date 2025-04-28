package me.dwidar.aflamy.shell.presentation.main_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import me.dwidar.aflamy.core.presentation.main_screen.MainScreenViewModel
import me.dwidar.aflamy.shell.configs.AflamyTheme
import me.dwidar.aflamy.shell.configs.getGeneralHorizontalPadding
import me.dwidar.aflamy.shell.configs.spacerHeight
import me.dwidar.aflamy.shell.presentation.common.AflamySearchBar
import me.dwidar.aflamy.shell.presentation.main_screen.components.MoviesCollection
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import me.dwidar.aflamy.core.presentation.main_screen.MainScreenIntent
import me.dwidar.aflamy.shell.presentation.movie_details.MovieDetailsActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainPage()
        }
    }
}

@Composable
fun MainPage(viewModel: MainScreenViewModel = viewModel()) {
    val state = viewModel.state.collectAsState()

    viewModel.onIntent(MainScreenIntent.GetPopularMovies)

    val context = LocalContext.current

    AflamyTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .safeContentPadding()
                    .padding(horizontal = getGeneralHorizontalPadding()),
                verticalArrangement = Arrangement.Top)
            {
                Spacer(modifier = Modifier.fillMaxHeight(spacerHeight))

                Text("Aflamy App", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.fillMaxHeight(spacerHeight))

                AflamySearchBar("") {}

                Spacer(modifier = Modifier.fillMaxHeight(spacerHeight))

                Text("Popular Movies", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.fillMaxHeight(spacerHeight))

                if (!state.value.isLoading){
                    MoviesCollection(
                        moviesGroup = state.value.moviesGroupByYears,
                        yearsList = state.value.descendingYears
                    ){ movieId ->
                        val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                            putExtra("movie_id", movieId)
                        }
                        context.startActivity(intent)
                    }
                }else Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                    CircularProgressIndicator()
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainPage()
}