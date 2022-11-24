package com.nikhil.cryptocurrency.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nikhil.cryptocurrency.presentation.ui.coin.details.CoinDetailsScreen
import com.nikhil.cryptocurrency.presentation.ui.coin.list.CoinListScreen
import com.nikhil.cryptocurrency.presentation.ui.theme.CryptocurrencyAppYTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppYTTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CryptoNavigation()
                }
            }
        }
    }
}

@Composable
fun CryptoNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.CoinListScreen.route,
    ) {
        composable(
            route = Screen.CoinListScreen.route
        ) {
            CoinListScreen(navController)
        }

        composable(
            route = Screen.CoinDetailsScreen.route + "/{coinId}",
            arguments = listOf(
                navArgument("coinId") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )) {
            CoinDetailsScreen(navController)
        }

    }
}