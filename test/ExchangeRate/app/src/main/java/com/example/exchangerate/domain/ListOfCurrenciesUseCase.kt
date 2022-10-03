package com.example.exchangerate.domain

class ListOfCurrenciesUseCase (private val listOfCurrenciesRepo: ListOfCurrenciesRepo) {

    suspend fun getCurrencies() = listOfCurrenciesRepo.getCurrencies()

}