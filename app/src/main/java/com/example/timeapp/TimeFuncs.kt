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
