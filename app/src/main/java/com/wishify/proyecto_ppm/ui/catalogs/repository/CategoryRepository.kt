package com.wishify.proyecto_ppm.ui.catalogs.repository

import android.util.Log
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.networking.response.WishCategory
import com.wishify.proyecto_ppm.networking.response.WishProduct

class CategoryRepository(private val webService: WishWebService = WishWebService()) {

    suspend fun getWishCategories(): List<WishCategory> {
        val categories = webService.getWishCategories()
        Log.d("CategoryRepository", "Fetched categories: $categories")
        return categories
    }

    suspend fun filterByCategory(categoryID: Int): List<WishProduct> {
        val products = webService.getCategoryFilter(categoryID)
        Log.d("CategoryRepository", "Fetched products for category $categoryID: $products")
        return products
    }
}