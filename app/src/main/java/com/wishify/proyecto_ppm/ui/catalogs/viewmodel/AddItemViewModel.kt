package com.wishify.proyecto_ppm.ui.catalogs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.catalogs.repository.AddItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddItemViewModel(private val repository: AddItemRepository) : ViewModel() {

    private val _categoryName = MutableStateFlow("Cargando...")
    val categoryName: StateFlow<String> = _categoryName

    private val _productImageUrl = MutableStateFlow<String?>(null)
    val productImageUrl: StateFlow<String?> = _productImageUrl

    private var categoryID = 0

    fun loadProductData(productID: Int) {
        viewModelScope.launch {
            try {
                val categories = repository.getCategories()
                for (category in categories) {
                    val products = repository.getProductsByCategory(category.id)
                    val product = products.find { it.itemID == productID }
                    if (product != null) {
                        _categoryName.value = category.category
                        _productImageUrl.value = product.imageUrl
                        categoryID = category.id
                        break
                    }
                }
                if (_categoryName.value == "Cargando...") {
                    _categoryName.value = "Categoría no encontrada"
                }
            } catch (e: Exception) {
                _categoryName.value = "Error al cargar categoría"
            }
        }
    }

    fun addProductToDatabase(codeList: String, productID: Int, navController: NavController) {
        repository.addProductToFirestore(
            codeList,
            categoryID,
            productID,
            onSuccess = { navController.navigate(NavigationState.AllLists.route) },
            onError = { println(it) }
        )
    }
}
