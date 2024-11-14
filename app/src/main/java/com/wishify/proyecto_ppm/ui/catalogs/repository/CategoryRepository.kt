package com.wishify.proyecto_ppm.ui.catalogs.repository

import android.util.Log
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.networking.response.WishCategory
import com.wishify.proyecto_ppm.networking.response.WishProduct

class CategoryRepository(private val webService: WishWebService = WishWebService()) {

    suspend fun getWishCategories(): List<WishCategory> {
        return webService.getWishCategories()
    }

    suspend fun filterByCategory(categoryID: Int): List<WishProduct> {
        return webService.getCategoryFilter(categoryID)
    }
}