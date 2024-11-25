package com.wishify.proyecto_ppm.ui.wishLists.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wishify.proyecto_ppm.ui.wishLists.repository.ListData
import com.wishify.proyecto_ppm.ui.wishLists.repository.MainListsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainListsViewModel(private val repository: MainListsRepository) : ViewModel() {

    private val _lists = MutableStateFlow<List<ListData>>(emptyList())
    val lists: StateFlow<List<ListData>> = _lists

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadLists()
    }

    fun loadLists() {
        viewModelScope.launch {
            _isLoading.value = true
            _lists.value = repository.fetchLists()
            _isLoading.value = false
        }
    }

    fun deleteList(codeList: String) {
        viewModelScope.launch {
            val success = repository.deleteList(codeList)
            if (success) {
                _lists.value = _lists.value.filter { it.codeList != codeList }
            }
        }
    }
}
