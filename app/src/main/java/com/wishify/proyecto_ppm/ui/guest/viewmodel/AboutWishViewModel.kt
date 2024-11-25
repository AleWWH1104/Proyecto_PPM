package com.wishify.proyecto_ppm.ui.guest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wishify.proyecto_ppm.ui.guest.repository.AboutWishRepository
import kotlinx.coroutines.launch

class AboutWishViewModel(private val repository: AboutWishRepository) : ViewModel() {
    val categoryName = mutableStateOf("Cargando...")
    val productImageUrl = mutableStateOf<String?>(null)

    fun fetchCategoryAndImage(productID: Int) {
        viewModelScope.launch {
            try {
                val (category, imageUrl) = repository.getCategoryAndImage(productID)
                categoryName.value = category
                productImageUrl.value = imageUrl
            } catch (e: Exception) {
                categoryName.value = "Error al cargar datos"
                println("Error: ${e.message}")
            }
        }
    }

    fun removeItemFromDatabase(codeList: String, productID: Int, onSuccess: () -> Unit) {
        repository.removeItemFromDatabase(
            codeList = codeList,
            productID = productID,
            onSuccess = onSuccess,
            onFailure = { e -> println("Error: ${e.message}") }
        )
    }
}
