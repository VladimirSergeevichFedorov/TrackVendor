package com.example.exchangerate.domain.entities

import java.math.BigDecimal

data class ExchangeRate(
    val id: String,
    val valute: String,
    val value: BigDecimal
)

