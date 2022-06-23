package com.example.trackvendor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackvendor.storage.entities.ConnectionState
import com.example.trackvendor.usecases.StorageInterface
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class MainActivityViewModel @Inject constructor(private val storageInterface: StorageInterface) :
    ViewModel() {

    private val _connectStateFlow =
        MutableStateFlow<List<ConnectionState>>(emptyList())
    val connectStateFlow: StateFlow<List<ConnectionState>>
        get() = _connectStateFlow

    fun getData() {
        viewModelScope.launch {
            storageInterface.getConnectData().collect { connect ->
                _connectStateFlow.emit(connect)
            }
        }
    }

    fun  clear(){
        viewModelScope.launch(Dispatchers.IO) {
            storageInterface.clearAllData()
        }
    }
}