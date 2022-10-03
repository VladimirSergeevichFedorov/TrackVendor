package com.example.exchangerate.mapping

import com.example.exchangerate.data.remote.Valute
import com.example.exchangerate.domain.entities.ExchangeRate

interface DataMapper {
    fun mapToGetValute(valute: Valute): ExchangeRate
}