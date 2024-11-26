package com.wishify.proyecto_ppm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.wishify.proyecto_ppm.ui.account.view.*
import com.wishify.proyecto_ppm.ui.catalogs.view.AddItem
import com.wishify.proyecto_ppm.ui.catalogs.view.Categories
import com.wishify.proyecto_ppm.ui.catalogs.view.ProductsByCategory
import com.wishify.proyecto_ppm.ui.guest.view.AboutWish
import com.wishify.proyecto_ppm.ui.guest.view.GuestScreen
import com.wishify.proyecto_ppm.ui.wishLists.view.AddList
import com.wishify.proyecto_ppm.ui.wishLists.view.MainLists
import com.wishify.proyecto_ppm.ui.wishLists.view.ViewList
import com.wishify.proyecto_ppm.ui.guest.view.ViewGuestList

@Composable
fun AppNavigation(navController: NavHostController){

    NavHost(navController = navController, startDestination = NavigationState.Home.route) {
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
        //composable(route = NavigationState.InfoItem.route){
        //    AboutWish(navController)
        //}
        composable(route = NavigationState.NewList.route){
            AddList(navController)
        }
        composable(route = NavigationState.Categories.route,
            arguments = listOf(
                navArgument("codeList") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            Categories(navController = navController, codeList = codeList)
        }
        composable(route = NavigationState.ProductsByCategory.route,
            arguments = listOf(
                navArgument("category") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType },
                navArgument("codeList") { type = NavType.StringType; defaultValue = "" }
            )
        ){  backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val categoryID = arguments.getInt("category")
            val title = backStackEntry.arguments?.getString("title") ?: "Category"
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            ProductsByCategory(categoryID,title, codeList, navController)
        }

        composable(
            route = NavigationState.addDetail.route,
            arguments = listOf(
                navArgument("codeList") { type = NavType.StringType },
                navArgument("productID") { type = NavType.IntType },
                navArgument("productName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            val productID = backStackEntry.arguments?.getInt("productID") ?: 0
            val productName = backStackEntry.arguments?.getString("productName") ?: ""

            AddItem(
                navController = navController,
                codeList = codeList,
                productID = productID,
                productName = productName
            )
        }

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

        //rutas para guest
        composable(
            route = "view_guest_list/{codeList}",
            arguments = listOf(navArgument("codeList") { type = NavType.StringType })
        ) { backStackEntry ->
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            ViewGuestList(navController = navController, codeList = codeList)
        }

        composable(
            route = NavigationState.AboutWish.route,
            arguments = listOf(
                navArgument("codeList") { type = NavType.StringType },
                navArgument("productID") { type = NavType.IntType },
                navArgument("productName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val codeList = backStackEntry.arguments?.getString("codeList") ?: ""
            val productID = backStackEntry.arguments?.getInt("productID") ?: 0
            val productName = backStackEntry.arguments?.getString("productName") ?: ""

            AboutWish(
                navController = navController,
                codeList = codeList,
                productID = productID,
                productName = productName
            )
        }
    }
}