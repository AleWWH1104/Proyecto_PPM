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
import com.wishify.proyecto_ppm.ui.wishLists.view.AddList
import com.wishify.proyecto_ppm.ui.wishLists.view.MainLists
import com.wishify.proyecto_ppm.ui.wishLists.view.ViewList

@Composable
fun AppNavigation(navController: NavHostController){

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
        //composable(route = NavigationState.MyList.route){
        //    ViewList(navController)
        //}
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
        //composable(route = NavigationState.Categories.route){
        //    Categories(navController)
        //}
        composable(
            route = NavigationState.Categories.route,
            arguments = listOf(navArgument("codeList") { type = NavType.StringType })
        ) { backStackEntry ->
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            Categories(navController = navController, codeList = codeList)
        }

        //composable(route = NavigationState.ProductsByCategory.route,
        //    arguments = listOf(
        //        navArgument("category") { type = NavType.IntType }
        //    )
        //){  backStackEntry ->
        //    val arguments = requireNotNull(backStackEntry.arguments)
        //    val categoryID = arguments.getInt("category")
        //    ProductsByCategory(categoryID = categoryID, navController = navController)
        //}

        composable(
            route = NavigationState.ProductsByCategory.route,
            arguments = listOf(navArgument("category") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryID = backStackEntry.arguments?.getInt("category") ?: 0
            ProductsByCategory(navController = navController, categoryID = categoryID)
        }


        composable(route = NavigationState.addDetail.route){
            AddItem(navController)
        }

        // ruta nueva para manejar el poder pasar datos de code list
        // Nueva ruta para manejar MyList con argumento CodeList
        composable(
            route = "MyList/{codeList}",
            arguments = listOf(
                navArgument("codeList") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            ViewList(navController = navController, codeList = codeList)
        }


    }
}