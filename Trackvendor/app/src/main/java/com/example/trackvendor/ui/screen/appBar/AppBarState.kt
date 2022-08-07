package com.example.trackvendor.ui.screen.appBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class AppBarState(
    val actions: (@Composable RowScope.() -> Unit)? = null
)
