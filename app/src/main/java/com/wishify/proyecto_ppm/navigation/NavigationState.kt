package com.wishify.proyecto_ppm.navigation

import com.wishify.proyecto_ppm.networking.response.WishProduct

sealed class NavigationState(val route: String)  {
    data object Home: NavigationState("home")
    data object Profile: NavigationState("profile")
    data object SignIn: NavigationState("signIn")
    data object SignUp: NavigationState("signUp")
    data object AllLists: NavigationState("allLists")
    data object MyList: NavigationState("myList")
    data object InfoItem: NavigationState("lookup")

    data object NewList: NavigationState("newList")

    //Retrofit API
    data object Categories {
        const val route = "categories/{codeList}"
        fun createRoute(codeList: String): String = "categories/$codeList"
    }

    data object ProductsByCategory : NavigationState("products_by_category/{category}?codeList={codeList}") {
        fun createRoute(categoryID: Int, codeList: String): String =
            "products_by_category/$categoryID?codeList=$codeList"
    }

    //data object addDetail : NavigationState("addDetail/{codeList}") {
    //    fun createRoute(codeList: String): String = "addDetail/$codeList"
    //}
    data object addDetail : NavigationState("addDetail/{codeList}/{productID}/{productName}") {
        fun createRoute(codeList: String, product: WishProduct): String {
            return "addDetail/$codeList/${product.itemID}/${product.nameItem}"
        }
    }



}