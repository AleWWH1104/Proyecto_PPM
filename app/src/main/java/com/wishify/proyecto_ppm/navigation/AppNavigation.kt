package com.wishify.proyecto_ppm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.wishify.proyecto_ppm.ui.account.view.*
import com.wishify.proyecto_ppm.ui.catalogs.view.AddItem
import com.wishify.proyecto_ppm.ui.catalogs.view.Categories
import com.wishify.proyecto_ppm.ui.catalogs.view.ProductsByCategory
import com.wishify.proyecto_ppm.ui.guest.AboutWish
import com.wishify.proyecto_ppm.ui.guest.GuestScreen
import com.wishify.proyecto_ppm.ui.wishLists.view.AddList
import com.wishify.proyecto_ppm.ui.wishLists.view.MainLists
import com.wishify.proyecto_ppm.ui.wishLists.view.ViewList
import com.wishify.proyecto_ppm.R
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(navController: NavHostController){

    NavHost(navController = navController, startDestination = NavigationState.Home.route ) {
        //Inicio de Sesion
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
        composable(route = NavigationState.Guest.route){
            GuestScreen(navController)
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
        composable(route = NavigationState.CategoriesFilter.route,
            arguments = listOf(
                navArgument("category") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType }
            )
        ){  backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val categoryID = arguments.getInt("category")
            val title = backStackEntry.arguments?.getString("title") ?: "Category"
            ProductsByCategory(categoryID,title,navController)
        }

        composable(route = NavigationState.addItemDetail.route,
            arguments = listOf(
                navArgument("nameItem") { type = NavType.StringType },
                navArgument("imgItem") { type = NavType.StringType }
            )
        ){ backStackEntry ->
            val nameItem = backStackEntry.arguments?.getString("nameItem")?: "Product Name"
            val imgItem = backStackEntry.arguments?.getString("imgItem") ?: "Default Img URL"
            AddItem(navController,nameItem, imgItem)
        }
    }
}