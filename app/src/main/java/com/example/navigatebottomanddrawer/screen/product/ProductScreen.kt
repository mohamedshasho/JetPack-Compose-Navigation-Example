package com.example.navigatebottomanddrawer.screen.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.navigatebottomanddrawer.navigation.Router
import com.example.navigatebottomanddrawer.navigation.Screen


@Composable
fun ProductScreen(router: Router) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier.clickable {
                router.navigateTo(Screen.ProductDetails.route)
            },
            text = "Product Screen"
        )
    }
}