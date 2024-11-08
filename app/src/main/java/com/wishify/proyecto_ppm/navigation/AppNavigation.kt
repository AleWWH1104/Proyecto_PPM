package com.wishify.proyecto_ppm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.wishify.proyecto_ppm.ui.account.view.*
import com.wishify.proyecto_ppm.ui.catalogs.view.AddItem
import com.wishify.proyecto_ppm.ui.catalogs.view.Categories
import com.wishify.proyecto_ppm.ui.catalogs.view.ProductsByCategory
import com.wishify.proyecto_ppm.ui.guest.AboutWish
import com.wishify.proyecto_ppm.ui.wishLists.view.AddList
import com.wishify.proyecto_ppm.ui.wishLists.view.MainLists
import com.wishify.proyecto_ppm.ui.wishLists.view.ViewList

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationState.Home.route ) {
        composable(route = NavigationState.Home.route){
            HomeScreen(navController)
        }
        composable(route = NavigationState.SignIn.route){
            SignInScreen(navController)
        }
        composable(route = NavigationState.SignUp.route){
            SignUpScreen(navController)
        }
        composable(route = NavigationState.Profile.route){
            UserAccount(navController)
        }
        composable(route = NavigationState.AllLists.route){
            MainLists(navController)
        }
        composable(route = NavigationState.MyList.route){
            ViewList(navController)
        }
        composable(route = NavigationState.InfoItem.route){
            AboutWish(navController)
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
            AddList(navController)
        }
        composable(route = NavigationState.Categories.route){
            Categories(navController)
        }
        composable(route = NavigationState.CategoriesFilter.route){
            ProductsByCategory(category = "Category", navController = navController )
        }
        composable(route = NavigationState.addDetail.route){
            AddItem(navController)
        }
//        composable(route = NavigationState.Categories.route){
//            Categories()
//        }
//        composable(NavigationState.CategoriesFilter.route,
//            arguments = listOf(
//                navArgument("category") { type = NavType.StringType }
//            )
//        ) { backStackEntry ->
//            val arguments = requireNotNull(backStackEntry.arguments)
//            val categoryName = arguments.getString("category") ?: ""
//            ProductsCategory(category = categoryName)
//        }


    }
}