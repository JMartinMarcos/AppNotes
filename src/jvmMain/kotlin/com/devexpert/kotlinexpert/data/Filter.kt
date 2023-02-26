package com.devexpert.kotlinexpert.data

sealed interface Filter {
    object All : Filter
    class ByType(val type: Note.TypeNote) : Filter
}