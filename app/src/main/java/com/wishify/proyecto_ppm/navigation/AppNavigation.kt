package com.wishify.proyecto_ppm.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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

        composable(route = NavigationState.InfoItem.route){
            AboutWish(navController)
        }

        composable(route = NavigationState.NewList.route){
            AddList(navController)
        }

        composable(
            route = NavigationState.Categories.route,
            arguments = listOf(navArgument("codeList") { type = NavType.StringType })
        ) { backStackEntry ->
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            Categories(navController = navController, codeList = codeList)
        }

        composable(
            route = NavigationState.ProductsByCategory.route,
            arguments = listOf(
                navArgument("category") { type = NavType.IntType },
                navArgument("codeList") { type = NavType.StringType; defaultValue = "" }
            )
        ) { backStackEntry ->
            val categoryID = backStackEntry.arguments?.getInt("category") ?: 0
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            ProductsByCategory(categoryID = categoryID, codeList = codeList, navController = navController, viewModel = viewModel())
        }





        composable(
            route = NavigationState.addDetail.route,
            arguments = listOf(navArgument("codeList") { type = NavType.StringType })
        ) { backStackEntry ->
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            AddItem(navController = navController, codeList = codeList)
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