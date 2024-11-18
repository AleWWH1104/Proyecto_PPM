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
    //data object Categories: NavigationState("categories")
    data object Categories {
        const val route = "categories/{codeList}"
        fun createRoute(codeList: String): String = "categories/$codeList"
    }

    data object ProductsByCategory: NavigationState("products_by_category/{category}"){
        fun createRoute(categoryID: Int) = "products_by_category/$categoryID"
    }

    //data object CategoriesFilter: NavigationState("categories/{category}"){
    //    fun createRoute(categoryID: Int) = "categories/$categoryID"
    //}
    data object addDetail: NavigationState("addDetail/{id}")

//    data object addDetail: NavigationState("addDetail/{id}"){
//        fun createRoute(id: String) = "addDetail/$id"
//    }
}