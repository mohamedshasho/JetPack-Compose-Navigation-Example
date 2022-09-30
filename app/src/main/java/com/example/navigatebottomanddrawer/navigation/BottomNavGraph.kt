package com.example.navigatebottomanddrawer.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigatebottomanddrawer.R
import com.example.navigatebottomanddrawer.screen.product.ProductScreen
import com.example.navigatebottomanddrawer.screen.profile.ProfileScreen


@Composable
fun BottomNavGraph(bottomNavController: NavHostController, rootRouter: Router) {

    NavHost(
        navController = bottomNavController,
        startDestination = BottomBarScreen.Product.route,
        route = "HOME",
    ) {
//        bottomNavGraph(rootNavController, scaffoldState = scaffoldState) todo delete this
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
        BottomBarScreen("product", title = "Home", icon = R.drawable.ic_baseline_home_24)

    object Profile :
        BottomBarScreen(route = "profile", title = "Translate", icon = R.drawable.ic_person)

}

val bottomScreens = listOf(
    BottomBarScreen.Product,
    BottomBarScreen.Profile,
)
