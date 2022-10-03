package com.example.exchangerate.mapping

import com.example.exchangerate.data.remote.Valute
import com.example.exchangerate.domain.entities.ExchangeRate
import javax.inject.Inject

class MappersValuteData @Inject constructor(): DataMapper {
    override fun mapToGetValute(valute: Valute) = ExchangeRate(
        id = valute.id,
        value = valute.value.toBigDecimal(),
        valute = valute.charCode
    )
}