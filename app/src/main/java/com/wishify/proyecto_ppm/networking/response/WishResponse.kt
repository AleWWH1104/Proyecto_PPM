package com.wishify.proyecto_ppm.networking.response

import com.google.gson.annotations.SerializedName

data class WishCategory(
    @SerializedName("id") val id: Int,
    @SerializedName("img") val imageUrl: String,
    @SerializedName("nombre") val category: String
)

data class WishProduct(
    @SerializedName("categoriaId") val categoryID: Int,
    @SerializedName("id") val itemID: Int,
    @SerializedName("nombre") val nameItem: String,
    @SerializedName("img") val imageUrl: String
)
