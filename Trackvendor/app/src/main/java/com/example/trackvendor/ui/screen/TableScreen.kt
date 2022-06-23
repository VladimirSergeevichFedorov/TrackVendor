package com.example.trackvendor.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.trackvendor.R
import com.example.trackvendor.ui.MainActivityViewModel

@Composable
fun TableScreen(
    viewModel: MainActivityViewModel
) {
    val usersData by viewModel.connectStateFlow.collectAsState()
    val context = LocalContext.current
    viewModel.getData()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.onPrimary)) {
        LazyColumn(modifier = Modifier
            .padding(bottom = dimensionResource(R.dimen.fifty_padding))
            .background(MaterialTheme.colors.onPrimary)) {
            if (usersData.isNotEmpty()) {

                usersData.forEach { userDataForTape ->
                    val isNameWifi =  if (!userDataForTape.nameWifi.orEmpty().contains(context.getString(R.string.unknown_ssid))) userDataForTape.nameWifi.orEmpty() else context.getString(R.string.unknown_name_wifi)
                    item {
                        Column(
                            modifier = Modifier
                                .padding(start = dimensionResource(R.dimen.six_padding))
                                .fillMaxWidth()
                        ) {
                            Text(
                                stringResource(R.string.wifi_name) + isNameWifi,
                                modifier = Modifier.padding(top = dimensionResource(R.dimen.fifteen_padding))
                            )
                            Text(stringResource(R.string.connection_date_wifi) + userDataForTape.connectingChangeData.orEmpty())
                            if (userDataForTape.stateWiFi == true) {
                                Text(
                                    stringResource(R.string.state_wifi) + stringResource(R.string.turn_on),
                                    color = colorResource(R.color.TextOn),
                                    fontWeight = FontWeight.Black,
                                )
                            } else {
                                Text(
                                    stringResource(R.string.state_wifi) + stringResource(R.string.turn_off),
                                    fontWeight = FontWeight.Black,
                                    color = colorResource(R.color.TextOff)
                                )
                            }
                        }
                    }
                }
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = dimensionResource(R.dimen.five_padding))
                .alpha(  0.85F),
            shape = RoundedCornerShape(integerResource(id = R.integer.rounded_corners_for_button)),
            onClick = { viewModel.clear() }) {
            Text(
                text = stringResource(R.string.delete_all_data),
                color = MaterialTheme.colors.onSecondary
            )
        }
    }

}