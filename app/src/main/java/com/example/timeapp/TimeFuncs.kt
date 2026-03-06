package com.example.timeapp

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun getTime(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val current = LocalDateTime.now().format(formatter)
        return current.format(formatter)
    }

fun getDate(): String {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return LocalDateTime.now().format(formatter)
    }

fun getMonth(): String{
    val formatter = DateTimeFormatter.ofPattern("MMMM")
    return LocalDateTime.now().format(formatter).uppercase()
}

fun getYear(): String{
    val formatter = DateTimeFormatter.ofPattern("YYYY")
    return LocalDateTime.now().format(formatter)
}
