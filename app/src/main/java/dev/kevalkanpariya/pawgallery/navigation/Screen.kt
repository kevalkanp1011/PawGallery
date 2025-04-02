package dev.kevalkanpariya.pawgallery.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DogNavGraph: Screen("dog_navgraph")
    object GenerateDogs : Screen("generate_dogs")
    object RecentDogs : Screen("recent_dogs")
    object Settings : Screen("settings")
}