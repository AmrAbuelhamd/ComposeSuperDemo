package com.blogspot.soyamr.composesuperdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.blogspot.soyamr.composesuperdemo.first.FirstBody
import com.blogspot.soyamr.composesuperdemo.second.SecondBody
import com.blogspot.soyamr.composesuperdemo.third.ThirdBody
import com.blogspot.soyamr.composesuperdemo.ui.theme.ComposeSuperDemoTheme

class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                LayoutsCodelab()
            }
        }
    }
}

@Composable
fun LayoutsCodelab() {
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = ScaffoldScreen.fromRoute(
        backstackEntry.value?.destination?.route
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Dangerous, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.BrokenImage, contentDescription = null)
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.HideImage, contentDescription = null)
                }
            }
        }

    ) { innerPadding ->
        SuperDemoNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SuperDemoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ScaffoldScreen.First.name,
        modifier = modifier
    ) {
        composable(ScaffoldScreen.First.name) {
            FirstBody() { name ->
                navController.navigate(name)
            }
        }
        composable(ScaffoldScreen.Second.name) {
            SecondBody() { name ->
                navController.navigate(name)
            }
        }
        composable(ScaffoldScreen.Third.name) {
            ThirdBody() { name ->
                navController.navigate(name)
            }
        }
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    ComposeSuperDemoTheme() {
        LayoutsCodelab()
    }
}
