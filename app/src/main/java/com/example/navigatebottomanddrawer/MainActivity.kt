package com.example.navigatebottomanddrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.navigatebottomanddrawer.navigation.RootNavigationGraph
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            /**
             * implementation Root Nav Graph
             *,rememberAnimatedNavController from google library
             * */
            val navController = rememberAnimatedNavController()
            RootNavigationGraph(navController = navController)
        }
    }
}

