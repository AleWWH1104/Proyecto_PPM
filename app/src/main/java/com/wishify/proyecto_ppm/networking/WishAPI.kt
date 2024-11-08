package com.wishify.proyecto_ppm.networking

import com.wishify.proyecto_ppm.networking.response.CategoryResponse
import com.wishify.proyecto_ppm.networking.response.Constants
import com.wishify.proyecto_ppm.networking.response.FilterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WishAPI {
    //Categorias
    @GET(Constants.path_url_1)
    suspend fun getCategories(): CategoryResponse

    //Filtros por categoria
    @GET(Constants.path_url_2)
    suspend fun getFilter(@Query("categoriaId") categoryID : Int):FilterResponse
}