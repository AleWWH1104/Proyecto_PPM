package com.wishify.proyecto_ppm.ui.account.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import com.wishify.proyecto_ppm.ui.account.repository.SignInRepository

class SignInViewModel(private val repository: SignInRepository) : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val loading = mutableStateOf(false)

    fun tryLogIn(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        loading.value = true
        repository.signIn(email.value, password.value) { success, error ->
            loading.value = false
            if (success) {
                onSuccess()
            } else {
                onError(error ?: "Unknown error")
            }
        }
    }
}