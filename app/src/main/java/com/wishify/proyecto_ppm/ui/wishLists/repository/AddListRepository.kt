package com.wishify.proyecto_ppm.ui.wishLists.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random

class AddListRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun generateCodeList(): String {
        return Random.nextInt(10000, 99999).toString()
    }

    fun addCodeListToUser(uid: String, newCodeList: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        firestore.collection("UsuariosP").document("UsuarioP")
            .collection("UsuarioP").document(uid)
            .update("CodeList", FieldValue.arrayUnion(newCodeList))
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it) }
    }

    fun createList(
        codeList: String,
        listData: Map<String, Any>,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        firestore.collection("ListasP").document("ListaP")
            .collection("ListaP").document(codeList)
            .set(listData)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it) }
    }

    fun getCurrentUserUid(): String? {
        return auth.currentUser?.uid
    }
}
