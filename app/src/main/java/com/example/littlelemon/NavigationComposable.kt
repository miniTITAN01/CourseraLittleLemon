package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.composables.Home
import com.example.littlelemon.composables.Onboarding
import com.example.littlelemon.composables.Profile

@Composable
fun MyNavigation(navController: NavHostController, context: Context, menuItems: LiveData<List<MenuItemRoom>>? = null) {
    // Check if user is logged in by checking SharedPreferences
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val isUserLoggedIn = sharedPreferences.getString("firstName", null) != null

    // Determine the start destination
    val startDestination = if (isUserLoggedIn) Home.route else Onboarding.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable(Home.route) {
            Home(navController = navController, menuItems = menuItems)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
    }
}

