package com.wishify.proyecto_ppm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.wishify.proyecto_ppm.ui.account.view.*
import com.wishify.proyecto_ppm.ui.catalogs.view.Categories
import com.wishify.proyecto_ppm.ui.catalogs.view.ProductsCategory
import com.wishify.proyecto_ppm.ui.wishLists.view.AddList
import com.wishify.proyecto_ppm.ui.wishLists.view.MainLists

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavigationState.Begin.route ) {
        composable(route = NavigationState.Begin.route){
            Beginning()
        }
        composable(route = NavigationState.SignIn.route){
            SignIn()
        }
        composable(route = NavigationState.SignUp.route){
            SignUp()
        }
        composable(route = NavigationState.Home.route){
            MainLists()
        }
        composable(route = NavigationState.NewList.route){
            AddList()
        }
        composable(route = NavigationState.Profile.route){
            UserAccount()
        }
        composable(route = NavigationState.Categories.route){
            Categories()
        }
        composable(NavigationState.CategoriesFilter.route,
            arguments = listOf(
                navArgument("category") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val categoryName = arguments.getString("category") ?: ""
            ProductsCategory(category = categoryName)
        }
        composable(NavigationState.MealsLook.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ){ backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("id") ?: ""
            Meal_Detail_Screen(id = mealId, navController = navController)
        }

    }
}