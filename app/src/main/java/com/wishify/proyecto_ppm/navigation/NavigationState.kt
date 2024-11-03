package com.wishify.proyecto_ppm.navigation

sealed class NavigationState(val route: String)  {
    data object Home: NavigationState("home")
    data object Profile: NavigationState("profile")
    data object SignIn: NavigationState("signIn")
    data object SignUp: NavigationState("signUp")
    data object AllLists: NavigationState("allLists")
    data object MyList: NavigationState("myList")
    data object InfoItem: NavigationState("lookup")

//    data object MyList: NavigationState("myList/{listId}"){
//        fun createRoute(listId: Int) = "myList/$listId"
//    }
//    data object InfoItem: NavigationState("lookup/{idItem}"){
//        fun createRoute(idItem: Int) = "lookup/$idItem"
//    }
    data object NewList: NavigationState("newList")

    //Retrofit API
    data object Categories: NavigationState("categories")
    data object CategoriesFilter: NavigationState("categories/{products}")
    data object addDetail: NavigationState("addDetail/{id}")
//    data object Categories: NavigationState("categories")
//    data object CategoriesFilter: NavigationState("categories/{products}")  {
//        fun createRoute(products: String) = "categories/$products"
//    }
//    data object addDetail: NavigationState("addDetail/{id}"){
//        fun createRoute(id: String) = "addDetail/$id"
//    }
}