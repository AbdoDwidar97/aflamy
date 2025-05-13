package me.dwidar.aflamy.shell.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.*
import me.dwidar.aflamy.shell.presentation.main_screen.MainPage
import me.dwidar.aflamy.shell.presentation.movie_details.MovieDetailsPage

@Composable
fun NavigationManager() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
            MainPage(navController = navController)
        }

        composable(
            "details/{movieID}",
            arguments = listOf(navArgument("movieID") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieID")
            MovieDetailsPage(movieId = movieId ?: -1)
        }
    }
}