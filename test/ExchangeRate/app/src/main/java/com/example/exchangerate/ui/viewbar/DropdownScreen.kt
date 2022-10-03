package com.example.exchangerate.ui.viewbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.exchangerate.R
import com.example.exchangerate.utils.StateSorted

@Composable
fun DropdownScreen(
    stateSorted: MutableState<StateSorted>,
    mDisplayMenu: MutableState<Boolean>
){

    Row(
        horizontalArrangement = Arrangement.End
    ) {
        DropdownMenu(
            expanded = mDisplayMenu.value,
            modifier = Modifier,
            onDismissRequest = { mDisplayMenu.value = false }
        ) {

            DropdownMenuItem(onClick = {
                stateSorted.value = StateSorted.ALPHABETASCENDING
                mDisplayMenu.value = false
            }) { Text(text = stringResource(id = R.string.alphabet_ascending)) }

            DropdownMenuItem(onClick = {
                stateSorted.value = StateSorted.ALPHABETDESCENDING
                mDisplayMenu.value = false
            }) { Text(text = stringResource(id = R.string.alphabet_descending)) }

            DropdownMenuItem(onClick = {
                stateSorted.value = StateSorted.VALUEASCENDING
                mDisplayMenu.value = false
            }) {
                Text(text = stringResource(id = R.string.value_ascending))
            }

            DropdownMenuItem(onClick = {
                stateSorted.value = StateSorted.VALUEDESCENDING
                mDisplayMenu.value = false
            }) { Text(text = stringResource(id = R.string.value_descending)) }
        }
    }
}