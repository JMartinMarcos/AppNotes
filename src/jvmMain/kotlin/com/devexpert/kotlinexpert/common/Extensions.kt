package com.devexpert.kotlinexpert.common

import androidx.compose.runtime.MutableState

fun <T> MutableState<T>.update(updateValue: (T) -> T) {
    value = updateValue(value)
}