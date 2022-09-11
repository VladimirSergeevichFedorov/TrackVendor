package com.example.trackvendor.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.trackvendor.R
import com.example.trackvendor.ui.MainActivityViewModel
import com.example.trackvendor.ui.screen.appBar.AppBarState

@Composable
fun MainScreen(
    viewModel: MainActivityViewModel,
    onComposing: (AppBarState) -> Unit,
) {
    val usersData by viewModel.connectStateFlow.collectAsState()
    val context = LocalContext.current
    onComposing(
        AppBarState(
            actions = {
                IconButton(onClick = { viewModel.getData() }) {
                    Icon(
                        imageVector = Icons.Filled.CloudCircle,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                IconButton(onClick = { viewModel.clear() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        )
    )
    Column(
        modifier = Modifier.paint(
            painterResource(
                R.drawable.main_background
            ),
            contentScale = ContentScale.Crop
        ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.onPrimary)
        ) {
            if (usersData.isNotEmpty()) {

                usersData.forEach { userDataForTape ->
                    val isNameWifi = if (!userDataForTape.nameWifi.orEmpty()
                        .contains(context.getString(R.string.unknown_ssid))
                    ) userDataForTape.nameWifi.orEmpty() else context.getString(R.string.unknown_name_wifi)
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
    }
}
