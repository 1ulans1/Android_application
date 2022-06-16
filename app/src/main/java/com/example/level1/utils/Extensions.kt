package com.example.level1.utils

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it -> it.replaceFirstChar { it.uppercase() } }