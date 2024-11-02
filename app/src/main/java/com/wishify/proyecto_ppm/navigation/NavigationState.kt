package com.wishify.proyecto_ppm.navigation

sealed class NavigationState(val route: String)  {
    data object Begin: NavigationState("begin")
    data object Profile: NavigationState("profile")
    data object SignIn: NavigationState("signIn")
    data object SignUp: NavigationState("signUp")
    data object Home: NavigationState("home")
    data object NewList: NavigationState("newList")
    data object MyList: NavigationState("myList")
    data object AddItem: NavigationState("addItem")
    data object Categories: NavigationState("categories")
    data object CategoriesFilter: NavigationState("categories/{products}")  {
        fun createRoute(products: String) = "categories/$products"
    }
    data object addDetail: NavigationState("addDetail/{id}"){
        fun createRoute(id: String) = "addDetail/$id"
    }
    data object Info: NavigationState("lookup/{id}"){
        fun createRoute(id: String) = "lookup/$id"
    }
}