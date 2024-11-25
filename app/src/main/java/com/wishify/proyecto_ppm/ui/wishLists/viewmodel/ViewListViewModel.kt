package com.wishify.proyecto_ppm.ui.wishLists.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wishify.proyecto_ppm.networking.response.WishProduct
import com.wishify.proyecto_ppm.ui.wishLists.repository.ViewListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewListViewModel(private val repository: ViewListRepository) : ViewModel() {

    private val _listNameP = MutableStateFlow("")
    val listNameP: StateFlow<String> = _listNameP

    private val _eventP = MutableStateFlow("")
    val eventP: StateFlow<String> = _eventP

    private val _productList = MutableStateFlow<List<WishProduct>>(emptyList())
    val productList: StateFlow<List<WishProduct>> = _productList

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadData(codeList: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val listData = repository.getListData(codeList)
                _listNameP.value = listData["listNameP"] as? String ?: ""
                _eventP.value = listData["eventP"] as? String ?: ""
                val itemListProdID = listData["itemListProdID"] as? List<Int> ?: emptyList()
                if (itemListProdID.isNotEmpty()) {
                    _productList.value = repository.getProductsByIDs(itemListProdID)
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _isLoading.value = false
            }
        }
    }

    fun removeItem(codeList: String, productID: Int) {
        viewModelScope.launch {
            try {
                repository.removeItemFromDatabase(codeList, productID)
                loadData(codeList)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}
