package com.example.navigatebottomanddrawer.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigatebottomanddrawer.R
import com.example.navigatebottomanddrawer.screen.product.ProductScreen
import com.example.navigatebottomanddrawer.screen.profile.ProfileScreen



/**
 * Bottom Nav Graph
 * must have different controller,
 * and root router to navigate in full view screen
 * */
@Composable
fun BottomNavGraph(bottomNavController: NavHostController, rootRouter: Router) {

    /**
     * Here using NavHost instead AnimatedNavHost because not work fine between destination
     * */
    NavHost(
        navController = bottomNavController,
        startDestination = BottomBarScreen.Product.route,
        route = "HOME",
    ) {
        composable(
            route = BottomBarScreen.Product.route,
        ) {
            ProductScreen(rootRouter)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }

}


sealed class BottomBarScreen(
    val route: String, val title: String,
    @DrawableRes val icon: Int
) {
    object Product :
        BottomBarScreen("home", title = "Home", icon = R.drawable.ic_baseline_home_24)

    object Profile :
        BottomBarScreen(route = "profile", title = "Profile", icon = R.drawable.ic_person)

}

val bottomScreens = listOf(
    BottomBarScreen.Product,
    BottomBarScreen.Profile,
)
