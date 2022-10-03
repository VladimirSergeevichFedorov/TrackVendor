package com.example.exchangerate.domain

import com.example.exchangerate.domain.entities.ExchangeRate

interface ListOfCurrenciesRepo {

    suspend fun getCurrencies(): List<ExchangeRate>
}