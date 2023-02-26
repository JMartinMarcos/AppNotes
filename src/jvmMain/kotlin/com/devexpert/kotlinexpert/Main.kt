package com.devexpert.kotlinexpert

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.devexpert.kotlinexpert.ui.App

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}