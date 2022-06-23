package com.example.trackvendor.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trackvendor.NavigationDestination
import com.example.trackvendor.R

@Composable
fun MainScreen(
    navController: NavController
) {
    val offset = Offset(5.0f, 3.0f)
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

        Button(
            onClick = {
                navController.navigate(NavigationDestination.TableScreen.destination) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            shape = RoundedCornerShape(integerResource(id = R.integer.rounded_corners_for_button)),
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.fifty_padding))
                .alpha(0.85F)
        ) {
            Text(
                text = stringResource(R.string.tap_to_navigate),
                color = MaterialTheme.colors.onSecondary,
                letterSpacing = 1.3.sp
            )
        }
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.twenty_padding)),
            color = MaterialTheme.colors.primary,
            letterSpacing = 1.3.sp,
            style = TextStyle(
                fontSize = 16.sp,
                shadow = Shadow(
                    color = Color.White,
                    offset = offset,
                    blurRadius = 3f
                )
        )
        )
    }
}