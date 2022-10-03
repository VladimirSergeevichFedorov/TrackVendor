package com.example.exchangerate.data.remote

import com.example.exchangerate.domain.ListOfCurrenciesRepo
import com.example.exchangerate.domain.entities.ExchangeRate
import com.example.exchangerate.mapping.DataMapper
import javax.inject.Inject

class ListOfCurrenciesImpl @Inject constructor(
    private val rateController: RateController,
    private val mapper: DataMapper
) : ListOfCurrenciesRepo {

    override suspend fun getCurrencies(): List<ExchangeRate> =
        rateController.getRateAsync().valute.map { valute ->
            mapper.mapToGetValute(
                Valute(
                    id = valute.value.id,
                    charCode = valute.value.charCode,
                    value = valute.value.value,
                    numCode = valute.value.numCode,
                    nominal = valute.value.nominal,
                    name = valute.value.name,
                    previous = valute.value.previous
                )
            )
        }
}