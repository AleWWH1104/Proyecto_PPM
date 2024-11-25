package com.wishify.proyecto_ppm.ui.guest.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.networking.response.WishProduct
import kotlinx.coroutines.tasks.await

class GuestListRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val wishWebService: WishWebService = WishWebService()
) {
    suspend fun getGuestListDetails(codeList: String): Result<Map<String, Any>> {
        return try {
            val document = firestore.collection("ListasP")
                .document("ListaP")
                .collection("ListaP")
                .document(codeList)
                .get()
                .await()
            val data = mapOf(
                "listNameP" to (document.getString("listNameP") ?: ""),
                "eventP" to (document.getString("EventP") ?: ""),
                "itemListProdID" to ((document.get("itemListProdID") as? List<Long>)?.map { it.toInt() } ?: emptyList())
            )
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductsByIds(productIds: List<Int>): Result<List<WishProduct>> {
        return try {
            val allProducts = mutableListOf<WishProduct>()
            val categories = wishWebService.getWishCategories()

            for (category in categories) {
                val productsFromApi = wishWebService.getCategoryFilter(category.id)
                val matchingProducts = productsFromApi.filter { it.itemID in productIds }
                allProducts.addAll(matchingProducts)
            }

            Result.success(allProducts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
