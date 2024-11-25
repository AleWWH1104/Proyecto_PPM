package com.wishify.proyecto_ppm.ui.guest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wishify.proyecto_ppm.networking.response.WishProduct
import com.wishify.proyecto_ppm.ui.guest.repository.GuestListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GuestListViewModel(private val repository: GuestListRepository = GuestListRepository()) : ViewModel() {

    private val _listName = MutableStateFlow("")
    val listName: StateFlow<String> get() = _listName

    private val _event = MutableStateFlow("")
    val event: StateFlow<String> get() = _event

    private val _productList = MutableStateFlow<List<WishProduct>>(emptyList())
    val productList: StateFlow<List<WishProduct>> get() = _productList

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun loadGuestList(codeList: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val guestListResult = repository.getGuestListDetails(codeList)
            guestListResult.fold(
                onSuccess = { data ->
                    _listName.value = data["listNameP"] as String
                    _event.value = data["eventP"] as String
                    val productIds = data["itemListProdID"] as List<Int>

                    if (productIds.isNotEmpty()) {
                        val productsResult = repository.getProductsByIds(productIds)
                        productsResult.fold(
                            onSuccess = {
                                _productList.value = it
                                _isLoading.value = false
                            },
                            onFailure = {
                                _errorMessage.value = "Error al obtener productos: ${it.message}"
                                _isLoading.value = false
                            }
                        )
                    } else {
                        _isLoading.value = false
                    }
                },
                onFailure = {
                    _errorMessage.value = "Error al cargar la lista: ${it.message}"
                    _isLoading.value = false
                }
            )
        }
    }
}
