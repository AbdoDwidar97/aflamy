package me.dwidar.aflamy.shell.presentation.main_screen

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.dwidar.aflamy.core.presentation.main_screen.MainScreenIntent
import me.dwidar.aflamy.shell.navigation.NavigationManager
import me.dwidar.aflamy.shell.presentation.common.NormalMoviesList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NavigationManager()
        }
    }
}

@Composable
fun MainPage(navController: NavController, viewModel: MainScreenViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()

    viewModel.onIntent(MainScreenIntent.OnGetPopularMovies)

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

                AflamySearchBar(query = state.value.searchText) { q ->
                    viewModel.onIntent(MainScreenIntent.OnGetMoviesWithSearch(query = q))
                }

                Spacer(modifier = Modifier.fillMaxHeight(spacerHeight))

                if (state.value.searchText.isEmpty())
                    PopularMoviesSection(navController = navController, viewModel = viewModel)
                else MoviesSearchResultSection(viewModel = viewModel, navController = navController)
            }
        }
    }
}

@Composable
fun PopularMoviesSection(navController: NavController, viewModel: MainScreenViewModel)
{
    Text("Popular Movies", style = MaterialTheme.typography.titleMedium)

    Spacer(modifier = Modifier.fillMaxHeight(spacerHeight))

    if (!viewModel.state.collectAsState().value.isLoading){
        MoviesCollection(
            moviesGroup = viewModel.state.collectAsState().value.moviesGroupByYears,
            yearsList = viewModel.state.collectAsState().value.descendingYears
        ){ movieId ->
            navController.navigate(route = "details/$movieId")
        }
    }else Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        CircularProgressIndicator()
    }
}

@Composable
fun MoviesSearchResultSection(navController: NavController, viewModel: MainScreenViewModel)
{
    Text("Search Result", style = MaterialTheme.typography.titleMedium)

    Spacer(modifier = Modifier.fillMaxHeight(spacerHeight))

    if (!viewModel.state.collectAsState().value.isLoading){
        NormalMoviesList (
            movies = viewModel.state.collectAsState().value.moviesSearch
        ){ movieId ->
            navController.navigate(route = "details/$movieId")
        }
    }else Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainPage(navController = rememberNavController())
}