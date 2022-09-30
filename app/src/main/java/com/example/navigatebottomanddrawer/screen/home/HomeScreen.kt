package com.example.navigatebottomanddrawer.screen.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigatebottomanddrawer.navigation.*
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun HomeScreen(rootRouter: Router) {

    /** NavController for the bottom nav host destinations */
    val bottomNavController = rememberNavController()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = "Navigation Example")
            },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            if(scaffoldState.drawerState.isClosed){
                                scaffoldState.drawerState.open()
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                })
        },
        bottomBar = {
            BottomBar(
                navController = bottomNavController
            )
        },
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(fraction = .85f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color.LightGray)
                        .clickable {
                            rootRouter.navigateTo(Screen.SettingScreen.route)
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = "Setting")
                }

            }
        },
        drawerShape = CustomShape
    ) {

        Box(modifier = Modifier.padding(it)) {
            BottomNavGraph(bottomNavController,rootRouter)
        }

    }
}


object CustomShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rounded(
            RoundRect(
                0f,
                0f,
                size.width * 0.85f,/* width */
                size.height, /* height */
                topRightCornerRadius = CornerRadius(x = 20f, y = 20f),
                bottomRightCornerRadius = CornerRadius(x = 20f, y = 20f),
            )
        )
    }
}


@Composable
fun BottomBar(
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationAsState = navBackStackEntry?.destination?.route ?: ""


    BottomNavigation {
        bottomScreens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestinationAsState,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: String,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Image(
                modifier = Modifier.size(28.dp),
                painter = painterResource(id = screen.icon),
                contentDescription = screen.title,
            )
        },
        selected = screen.route == currentDestination,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}