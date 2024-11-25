package com.wishify.proyecto_ppm.ui.guest.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.networking.WishWebService

class AboutWishRepository(
    private val firestore: FirebaseFirestore,
    private val wishWebService: WishWebService
) {
    suspend fun getCategoryAndImage(productID: Int): Pair<String, String?> {
        val categories = wishWebService.getWishCategories()
        for (category in categories) {
            val products = wishWebService.getCategoryFilter(categoryID = category.id)
            val product = products.find { it.itemID == productID }
            if (product != null) {
                return category.category to product.imageUrl
            }
        }
        throw Exception("Producto no encontrado")
    }

    fun removeItemFromDatabase(
        codeList: String,
        productID: Int,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val documentRef = firestore.collection("ListasP").document("ListaP")
            .collection("ListaP").document(codeList)

        documentRef.get().addOnSuccessListener { documentSnapshot ->
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
                    ).addOnSuccessListener {
                        onSuccess()
                    }.addOnFailureListener { e ->
                        onFailure(e)
                    }
                } else {
                    onFailure(Exception("Producto no encontrado en la lista."))
                }
            } else {
                onFailure(Exception("No se pudieron obtener los arrays de Firestore."))
            }
        }.addOnFailureListener { e ->
            onFailure(e)
        }
    }
}
