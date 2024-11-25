package com.wishify.proyecto_ppm.ui.catalogs.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.networking.WishWebService

class AddItemRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val wishWebService = WishWebService()

    suspend fun getCategories() = wishWebService.getWishCategories()

    suspend fun getProductsByCategory(categoryID: Int) =
        wishWebService.getCategoryFilter(categoryID)

    fun addProductToFirestore(codeList: String, categoryID: Int, productID: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val documentRef = firestore.collection("ListasP").document("ListaP")
            .collection("ListaP").document(codeList)

        documentRef.update(
            "itemListCategID", FieldValue.arrayUnion(categoryID),
            "itemListProdID", FieldValue.arrayUnion(productID)
        ).addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Error desconocido") }
    }
}
