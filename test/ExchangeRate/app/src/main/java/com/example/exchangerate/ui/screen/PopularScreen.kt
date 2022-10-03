package com.example.exchangerate.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.exchangerate.ui.viewmodels.PopularViewModule
import com.example.exchangerate.R
import com.example.exchangerate.navigation.Navigation
import com.example.exchangerate.utils.StateSorted
import com.example.exchangerate.utils.flow

@Composable
fun PopularScreen(
    viewModel: PopularViewModule,
    navController: NavController,
    stateSearchText: MutableState<TextFieldValue>,
    stateSorted: MutableState<StateSorted>
) {
    val valuates by viewModel::valuatesFlow.flow.collectAsState()

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

        val searchText = stateSearchText.value.text

        val valuatesList =
            if (searchText.isNotEmpty()) valuates.filter { it.valute.contains(searchText.uppercase()) } else valuates

        val filterResult = when (stateSorted.value) {
            StateSorted.ALPHABETASCENDING -> valuatesList.sortedBy { it.valute }
            StateSorted.ALPHABETDESCENDING -> valuatesList.sortedBy { it.valute }
                .reversed()
            StateSorted.VALUEASCENDING -> valuatesList.sortedBy { it.value }
            StateSorted.VALUEDESCENDING -> valuatesList.sortedBy { it.value }
                .reversed()
            else -> {
                valuatesList
            }
        }
        Text(text = stringResource(id = R.string.header_list_valute))
        LazyColumn(
            modifier = Modifier
                .padding()
                .fillMaxWidth()
        ) {
            if (filterResult.isNotEmpty()) {
                item {
                    filterResult.forEach { valute ->
                        Row(
                            modifier = Modifier
                                .padding(
                                    start = dimensionResource(R.dimen.five_dp),
                                    end = dimensionResource(R.dimen.five_dp)
                                )
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = valute.valute)
                            Text(
                                modifier = Modifier.padding(start = dimensionResource(R.dimen.ten_dp)),
                                text = valute.value.toString()
                            )
                            IconButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .wrapContentSize(
                                        Alignment.CenterEnd
                                    ),
                                onClick = { navController.navigate(Navigation.Favorites.route) }
                            ) { Icon(Icons.Filled.StarBorder, null) }
                        }
                    }
                }
            }
        }
    }
}