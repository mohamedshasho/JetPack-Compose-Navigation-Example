package com.example.navigatebottomanddrawer.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.navigatebottomanddrawer.screen.home.HomeScreen
import com.example.navigatebottomanddrawer.screen.product.ProductDetails
import com.example.navigatebottomanddrawer.screen.setting.SettingScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
        route = "NAV_ROOT"
    ) {
        composable(
            route = Screen.MainScreen.route,
            enterTransition = {
                slideInHorizontally(animationSpec = tween(400)) + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(400)) + fadeOut()
            }
        ) {
            HomeScreen(
                createRouter { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = Screen.SettingScreen.route,
            enterTransition = { EnterTransition() },
            exitTransition = { ExitTransition() }
        ) {
            SettingScreen{
                navController.popBackStack()
            }
        }
        composable(
            route = Screen.ProductDetails.route,
            enterTransition = { EnterTransition() },
            exitTransition = { ExitTransition() }
        ) {
            ProductDetails{
                navController.popBackStack()
            }
        }

    }
}


fun EnterTransition() =
    slideInHorizontally(animationSpec = tween(400), initialOffsetX = { it / 2 }) + fadeIn()

fun ExitTransition() =
    slideOutHorizontally(animationSpec = tween(400), targetOffsetX = { it / 2 }) + fadeOut()

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object SettingScreen : Screen("setting")
    object ProductDetails : Screen("product_details")
}