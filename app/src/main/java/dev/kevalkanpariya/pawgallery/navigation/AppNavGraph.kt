package dev.kevalkanpariya.pawgallery.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.DogViewModel
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.GenerateDogScreen
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.RecentDogsScreen
import dev.kevalkanpariya.pawgallery.presentation.home.HomeScreen
import dev.kevalkanpariya.pawgallery.presentation.settings.SettingsScreen
import dev.kevalkanpariya.pawgallery.presentation.settings.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigate = { navController.navigate(it) }
            )
        }

        navigation(startDestination = Screen.GenerateDogs.route, route = Screen.DogNavGraph.route) {

            composable(Screen.GenerateDogs.route) { backStackEntry ->
                val viewModel = backStackEntry.sharedViewModel<DogViewModel>(navController)
                val state = viewModel.state.collectAsStateWithLifecycle().value
                GenerateDogScreen(
                    state = state,
                    onEvent = { viewModel.onEvent(it) },
                    onNavigateBack = {navController.popBackStack()},
                    onNavigate = navController::navigate
                )
            }

            composable(Screen.RecentDogs.route) { backStackEntry ->
                val viewModel = backStackEntry.sharedViewModel<DogViewModel>(navController)
                val state = viewModel.state.collectAsStateWithLifecycle().value
                RecentDogsScreen(
                    state = state,
                    onEvent = { viewModel.onEvent(it) },
                    onNavigateBack = {navController.popBackStack()},
                    onNavigate = navController::navigate
                )
            }
        }

        composable(Screen.Settings.route) {
            val viewModel = koinViewModel<SettingsViewModel>()
            SettingsScreen(
                viewModel = viewModel,
                onNavigateBack = {navController.popBackStack()},
                onNavigate = navController::navigate
            )
        }
    }
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return koinViewModel(viewModelStoreOwner = parentEntry)
}