package com.example.summaytask12.extensions

fun String.capitalizeFirst(): String {
    val normalizedName = this.lowercase()
    val nameParts = normalizedName.split(" ")
    val capitalizedParts = nameParts.map { it.capitalize() }
    return capitalizedParts.joinToString(" ")
}

fun String.reverseString(): String {
    return this.reversed()
}

fun String.lengthString(): Int {
    return this.length
}

