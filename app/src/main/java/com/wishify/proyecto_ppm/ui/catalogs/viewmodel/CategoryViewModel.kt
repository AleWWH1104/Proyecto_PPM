package com.wishify.proyecto_ppm.ui.catalogs.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wishify.proyecto_ppm.networking.response.WishCategory
import com.wishify.proyecto_ppm.networking.response.WishProduct
import com.wishify.proyecto_ppm.ui.catalogs.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(val repository: CategoryRepository = CategoryRepository()): ViewModel() {
    private val _categories = MutableLiveData<List<WishCategory>>()
    val categories: LiveData<List<WishCategory>> = _categories

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _products = MutableLiveData<List<WishProduct>>()
    val products: LiveData<List<WishProduct>> = _products

    fun fetchCategories() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val categories = repository.getWishCategories()
                _categories.postValue(categories)
                Log.d("CategoryViewModel", "Categories loaded: $categories")
            } catch (e: Exception) {
                Log.e("CategoryViewModel", "Error fetching categories", e)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun fetchByCategory(categoryID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = repository.filterByCategory(categoryID)
                _products.postValue(products)
                Log.d("CategoryViewModel", "Products loaded for category $categoryID: $products")
            } catch (e: Exception) {
                Log.e("CategoryViewModel", "Error fetching products for category $categoryID", e)
            }
        }
    }
}


