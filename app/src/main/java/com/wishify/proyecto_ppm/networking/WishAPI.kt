package com.wishify.proyecto_ppm.networking

import com.wishify.proyecto_ppm.networking.response.CategoryResponse
import com.wishify.proyecto_ppm.networking.response.Constants
import com.wishify.proyecto_ppm.networking.response.FilterResponse
import com.wishify.proyecto_ppm.networking.response.WishCategory
import com.wishify.proyecto_ppm.networking.response.WishProduct
import retrofit2.http.GET
import retrofit2.http.Query

interface WishAPI {
    //Categorias
    @GET(Constants.path_url_1)
    suspend fun getCategories():List<WishCategory>

    //Filtros por categoria
    @GET(Constants.path_url_2)
    suspend fun getFilter(@Query("categoriaId") categoryID : Int): List<WishProduct>
}