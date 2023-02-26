package com.devexpert.kotlinexpert.ui.screens.home

import com.devexpert.kotlinexpert.data.Filter
import com.devexpert.kotlinexpert.data.Filter.ByType
import com.devexpert.kotlinexpert.data.Note
import com.devexpert.kotlinexpert.data.getNoteList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

object HomeState {
    private var _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()

    fun loadNotes(coroutineScope: CoroutineScope) =
        coroutineScope.launch {
            getNoteList().collect { notes ->
                _state.update { UIState(notes = notes, loading = false) }
            }
        }

    fun onFilterClick(filter: Filter) {
        _state.update { it.copy(filter = filter, loading = false) }
    }

    data class UIState(
        val notes: List<Note> = emptyList(),
        val loading: Boolean = true,
        val filter: Filter = Filter.All
    ) {
        val filterNotes: List<Note>
            get() = when (filter) {
                Filter.All -> notes
                is ByType -> notes.filter { it.type == filter.type }
            }
    }

}