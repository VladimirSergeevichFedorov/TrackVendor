package com.example.exchangerate.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.exchangerate.R

@Composable
fun FavoritesScreen() {
    Column(
        modifier = Modifier
            .padding(
                top = dimensionResource(R.dimen.twenty_five_dp),
                start = dimensionResource(R.dimen.ten_dp),
                end = dimensionResource(R.dimen.ten_dp),
                bottom = dimensionResource(R.dimen.sixty_five_dp),
            )
            .fillMaxSize()
    ) {

        Text(text = stringResource(id = R.string.header_list_favorite_valute))
    }
}