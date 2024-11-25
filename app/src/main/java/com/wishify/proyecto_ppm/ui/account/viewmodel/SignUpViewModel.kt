package com.wishify.proyecto_ppm.ui.account.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import com.wishify.proyecto_ppm.ui.account.repository.SignUpRepository

class SignUpViewModel(private val repository: SignUpRepository) : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val loading = mutableStateOf(false)

    fun trySignUp(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        loading.value = true
        repository.signUp(email.value, password.value) { success, error ->
            loading.value = false
            if (success) {
                onSuccess()
            } else {
                onError(error ?: "Unknown error")
            }
        }
    }
}