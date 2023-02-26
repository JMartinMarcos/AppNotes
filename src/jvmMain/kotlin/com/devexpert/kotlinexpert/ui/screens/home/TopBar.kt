package com.devexpert.kotlinexpert.ui.screens.home

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.devexpert.kotlinexpert.data.Filter
import com.devexpert.kotlinexpert.data.Filter.All
import com.devexpert.kotlinexpert.data.Filter.ByType
import com.devexpert.kotlinexpert.data.Note.TypeNote.AUDIO
import com.devexpert.kotlinexpert.data.Note.TypeNote.TEXT


private val filterOptions = listOf(
    "All" to All,
    "Text" to ByType(TEXT),
    "Audio" to ByType(AUDIO)
)

@Composable
internal fun TopBar(onFilterClick: (Filter) -> Unit) {
    TopAppBar(
        title = { Text("My Notes") },
        actions = {
            FilterActions {
                onFilterClick(it)
            }
        }
    )
}

@Composable
private fun FilterActions(onFilterClick: (Filter) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    @Composable
    infix fun Filter.ToMenuItem(label: String) = DropdownMenuItem(
        onClick = {
            expanded = false
            onFilterClick(this)
        }
    ) {
        Text(label)
    }


    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter"
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            filterOptions.forEach { (text, filter) -> filter ToMenuItem text }
        }
    }
}