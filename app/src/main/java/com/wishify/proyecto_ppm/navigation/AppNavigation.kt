package com.wishify.proyecto_ppm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.wishify.proyecto_ppm.ui.account.view.*
import com.wishify.proyecto_ppm.ui.catalogs.view.Categories
import com.wishify.proyecto_ppm.ui.catalogs.view.ProductsCategory
import com.wishify.proyecto_ppm.ui.wishLists.view.AboutWish
import com.wishify.proyecto_ppm.ui.wishLists.view.AddList
import com.wishify.proyecto_ppm.ui.wishLists.view.MainLists
import com.wishify.proyecto_ppm.ui.wishLists.view.ViewList

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavigationState.Begin.route ) {
        composable(route = NavigationState.Begin.route){
            Beginning(navController)
        }
        composable(route = NavigationState.SignIn.route){
            SignInScreen(navController)
        }
        composable(route = NavigationState.SignUp.route){
            SignUpScreen(navController)
        }
        composable(route = NavigationState.Home.route){
            MainLists(navController)
        }
        composable(route = NavigationState.MyList.route){
            ViewList()
        }
        composable(route = NavigationState.InfoItem.route){
            AboutWish()
        }
//        composable(NavigationState.MyList.route) { backStackEntry ->
//            val listId = backStackEntry.arguments?.getString("listId")?.toInt() ?: 0
//            ViewList()
//        }
//        composable(NavigationState.InfoItem.route) { backStackEntry ->
//            val listId = backStackEntry.arguments?.getString("idItem")?.toInt() ?: 0
//            AboutWish()
//        }
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


    }
}