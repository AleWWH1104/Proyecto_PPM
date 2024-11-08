package com.wishify.proyecto_ppm.networking

import com.wishify.proyecto_ppm.networking.response.CategoryResponse
import com.wishify.proyecto_ppm.networking.response.Constants
import com.wishify.proyecto_ppm.networking.response.FilterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WishWebService {
    private lateinit var api: WishAPI //Interfaz

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(WishAPI::class.java)
    }

    //Categories
    suspend fun getWishCategories(): CategoryResponse {
        return api.getCategories()
    }

    //Filters
    suspend fun getCategoryFilter(categoryID: Int): FilterResponse {
        return api.getFilter(categoryID)
    }
}