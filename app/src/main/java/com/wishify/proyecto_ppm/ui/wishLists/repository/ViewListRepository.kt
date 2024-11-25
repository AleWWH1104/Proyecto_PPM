package com.wishify.proyecto_ppm.ui.wishLists.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.networking.response.WishProduct
import kotlinx.coroutines.tasks.await

class ViewListRepository {
    private val db = FirebaseFirestore.getInstance()
    private val wishWebService = WishWebService()

    suspend fun getListData(codeList: String): Map<String, Any?> {
        return try {
            val documentSnapshot = db.collection("ListasP")
                .document("ListaP")
                .collection("ListaP")
                .document(codeList)
                .get()
                .await()
            mapOf(
                "listNameP" to documentSnapshot.getString("listNameP"),
                "eventP" to documentSnapshot.getString("EventP"),
                "itemListProdID" to (documentSnapshot.get("itemListProdID") as? List<Long>)?.map { it.toInt() }
            )
        } catch (e: Exception) {
            emptyMap()
        }
    }

    suspend fun getProductsByIDs(itemListProdID: List<Int>): List<WishProduct> {
        return try {
            val categories = wishWebService.getWishCategories()
            categories.flatMap { category ->
                wishWebService.getCategoryFilter(category.id).filter { it.itemID in itemListProdID }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun removeItemFromDatabase(codeList: String, productID: Int) {
        val documentRef = db.collection("ListasP")
            .document("ListaP")
            .collection("ListaP")
            .document(codeList)
        try {
            val documentSnapshot = documentRef.get().await()
            val itemListProdID = (documentSnapshot.get("itemListProdID") as? List<Number>)?.map { it.toInt() }?.toMutableList()
            val itemListCategID = (documentSnapshot.get("itemListCategID") as? List<Number>)?.map { it.toInt() }?.toMutableList()

            if (itemListProdID != null && itemListCategID != null) {
                val indexToRemove = itemListProdID.indexOf(productID)
                if (indexToRemove != -1) {
                    itemListProdID.removeAt(indexToRemove)
                    itemListCategID.removeAt(indexToRemove)
                    documentRef.update(
                        mapOf(
                            "itemListProdID" to itemListProdID,
                            "itemListCategID" to itemListCategID
                        )
                    ).await()
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
