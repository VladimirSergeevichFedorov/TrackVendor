package com.example.exchangerate.ui.viewbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.exchangerate.utils.StateSorted

@Composable
fun TopBar(
    stateSearchText: MutableState<TextFieldValue>,
    stateSorted: MutableState<StateSorted>
) {
    val mDisplayMenu = remember { mutableStateOf(false) }

    TopAppBar(
        content = {
            SearchView(stateSearchText)
            IconButton(
                onClick = {
                    mDisplayMenu.value = !mDisplayMenu.value
                },
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize(
                        Alignment.CenterEnd
                    )
            ) { Icon(Icons.Default.Sort,null) }
            DropdownScreen(stateSorted,mDisplayMenu)
        },
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.secondary
    )
}