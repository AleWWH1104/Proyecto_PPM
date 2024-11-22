package com.wishify.proyecto_ppm.navigation

import com.wishify.proyecto_ppm.networking.response.WishProduct

sealed class NavigationState(val route: String)  {
    data object Home: NavigationState("home")
    data object Profile: NavigationState("profile")
    data object SignIn: NavigationState("signIn")
    data object SignUp: NavigationState("signUp")
    data object Guest: NavigationState("guest")
    data object AllLists: NavigationState("allLists")
    data object InfoItem: NavigationState("lookup")
    data object NewList: NavigationState("newList")

    //Retrofit API
    data object Categories {
        const val route = "categories/{codeList}"
        fun createRoute(codeList: String): String = "categories/$codeList"
    }
    data object ProductsByCategory : NavigationState("categories/{category}?codeList={codeList}/{title}"){
        fun createRoute(categoryID: Int,codeList: String, title: String) = "categories/$categoryID?codeList=$codeList/$title"
    }
//    data object addItemDetail: NavigationState("addDetail/{nameItem}/{imgItem}"){
//        fun createRoute(nameItem: String, imgItem: String) = "addDetail/$nameItem/$imgItem"
//    }
data object addDetail : NavigationState("addDetail/{codeList}/{productID}/{productName}") {
    fun createRoute(codeList: String, product: WishProduct): String {
        return "addDetail/$codeList/${product.itemID}/${product.nameItem}"
    }
}

data object MyList: NavigationState("myList")
}