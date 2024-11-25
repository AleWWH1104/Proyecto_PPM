package com.wishify.proyecto_ppm.ui.account.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    fun signUp(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid
                    if (uid != null) {
                        addUID(uid)
                        onComplete(true, null)
                    } else {
                        onComplete(false, "UID is null after sign-up")
                    }
                } else {
                    onComplete(false, task.exception?.localizedMessage)
                }
            }
    }

    private fun addUID(uid: String) {
        val userData = hashMapOf(
            "UID" to uid,
            "CodeList" to listOf("0")
        )
        firestore.collection("UsuariosP")
            .document("UsuarioP")
            .collection("UsuarioP")
            .document(uid)
            .set(userData)
            .addOnSuccessListener {
                println("Usuario agregado exitosamente en Firestore.")
            }
            .addOnFailureListener { e ->
                println("Error al agregar usuario: ${e.message}")
            }
    }
}