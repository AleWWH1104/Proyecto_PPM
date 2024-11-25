package com.wishify.proyecto_ppm.ui.account.repository

import com.google.firebase.auth.FirebaseAuth

class SignInRepository(private val auth: FirebaseAuth) {
    fun signIn(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null)
                } else {
                    onComplete(false, task.exception?.localizedMessage)
                }
            }
    }
}