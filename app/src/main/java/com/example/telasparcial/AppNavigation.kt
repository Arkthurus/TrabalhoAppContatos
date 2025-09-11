package com.example.telasparcial

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    var navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Tela1"
    ) {
        // Define a tela principal (Home)
        composable("Tela1"){MainActivity()}
        composable("Tela2"){}
        composable("tela3"){}
    }
}

