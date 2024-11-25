package com.wishify.proyecto_ppm.ui.wishLists.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wishify.proyecto_ppm.ui.wishLists.repository.AddListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Event(val text: String, val imageRes: Int)

class AddListViewModel(private val repository: AddListRepository) : ViewModel() {

    private val _listName = MutableStateFlow("")
    val listName: StateFlow<String> get() = _listName

    private val _selectedEvent = MutableStateFlow<Event?>(null)
    val selectedEvent: StateFlow<Event?> get() = _selectedEvent

    fun updateListName(newName: String) {
        _listName.value = newName
    }

    fun selectEvent(event: Event) {
        _selectedEvent.value = event
    }

    fun createList(
        onNavigate: () -> Unit,
        onError: (String) -> Unit
    ) {
        val codeList = repository.generateCodeList()
        val uid = repository.getCurrentUserUid()
        val event = _selectedEvent.value

        if (uid != null && event != null && _listName.value.isNotEmpty()) {
            val listData = hashMapOf(
                "CodeList" to codeList,
                "EventP" to event.text,
                "ImageRes" to event.imageRes,
                "itemListCategID" to emptyList<Int>(),
                "itemListProdID" to emptyList<Int>(),
                "listNameP" to _listName.value
            )

            viewModelScope.launch {
                repository.createList(codeList, listData,
                    onSuccess = {
                        repository.addCodeListToUser(uid, codeList,
                            onSuccess = { onNavigate() },
                            onError = { onError(it.message ?: "Error inesperado") }
                        )
                    },
                    onError = { onError(it.message ?: "Error inesperado") }
                )
            }
        } else {
            onError("Faltan campos por completar.")
        }
    }
}
