package com.example.exchangerate.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ValuteEntity(
    @PrimaryKey val valuteId: String = "",

    @ColumnInfo(name = "NumCode")
    val numCode: String = "",

    @ColumnInfo(name = "CharCode")
    val charCode: String = "",

    @ColumnInfo(name = "Nominal")
    val nominal: Long = 0,

    @ColumnInfo(name = "Name")
    val name: String? = "",

    @ColumnInfo(name = "Value")
    val value: Double = 0.0,

    @ColumnInfo(name = "Previous")
    val previous: Double = 0.0
)
