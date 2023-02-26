package com.devexpert.kotlinexpert.data

import com.devexpert.kotlinexpert.data.Note.TypeNote.AUDIO
import com.devexpert.kotlinexpert.data.Note.TypeNote.TEXT
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


data class Note(
    val title: String,
    val description: String,
    val type: TypeNote
) {
    enum class TypeNote { TEXT, AUDIO }
}

private const val LABEL_TITLE = "Title"
private const val LABEL_DESCRIPTION = "Description"

/* ejemplo flows
fun com.devexpert.kotlinexpert.data.getNoteList(): Flow<List<com.devexpert.kotlinexpert.data.Note>> = flow {
    delay(1000)
    val notes = mutableListOf<com.devexpert.kotlinexpert.data.Note>()
    (1..500).forEach { index ->
        notes.add(com.devexpert.kotlinexpert.data.Note("$com.devexpert.kotlinexpert.data.LABEL_TITLE  $index", "$com.devexpert.kotlinexpert.data.LABEL_DESCRIPTION  $index", com.devexpert.kotlinexpert.data.getTypeNote(index)))
        emit(notes.toList())
        delay(300)
    }
}
 */

fun getNoteList(): Flow<List<Note>> = flow {
    delay(1000)
    emit((1..500).map { index ->
        Note("$LABEL_TITLE  $index", "$LABEL_DESCRIPTION  $index", getTypeNote(index))
    })
}

private fun getTypeNote(index: Int) = if (index % 3 == 0) AUDIO else TEXT

