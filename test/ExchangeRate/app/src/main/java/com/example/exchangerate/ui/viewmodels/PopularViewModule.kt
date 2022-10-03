package com.example.exchangerate.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.data.remote.Valute
import com.example.exchangerate.domain.ListOfCurrenciesUseCase
import com.example.exchangerate.domain.entities.ExchangeRate
import com.example.exchangerate.utils.state
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PopularViewModule @Inject constructor(
    private val listOfCurrenciesUseCase: ListOfCurrenciesUseCase
) : ViewModel() {

    var valuatesFlow by state(emptyList<ExchangeRate>())
        private set

    init {
        viewModelScope.launch {
            valuatesFlow =  listOfCurrenciesUseCase.getCurrencies()
        }
    }
}