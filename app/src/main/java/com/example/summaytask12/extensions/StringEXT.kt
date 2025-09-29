package com.example.summaytask12.extensions

import com.example.summaytask12.sealed.StatusStudent

fun String.capitalizeFirst(): String {
    val nameParts = this.split(" ")
    val isConrectFormat = nameParts.all { part ->
        part.isNotEmpty() && part.first().isUpperCase() && part.drop(1).all { it.isLowerCase() }
    }
    return if (isConrectFormat) {
        this
    } else {
        nameParts.map { part ->
            part.lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }.joinToString(" ")
    }
}

//fun String.reverseString(): String {
//    return this.reversed()
//}
//
//fun String.lengthString(): Int {
//    return this.length
//}



