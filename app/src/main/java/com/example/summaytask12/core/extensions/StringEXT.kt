package com.example.summaytask12.core.extensions

fun String.capitalizeFirst(): String {
    val nameParts = this.split(" ")
    val isConrectFormat = nameParts.all { part ->
        part.isNotEmpty() && part.first().isUpperCase() && part.drop(1).all { it.isLowerCase() }
    }
    return if (isConrectFormat) {
        this
    } else {
        nameParts.joinToString(" ") { part ->
            part.lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
}

fun String.isValidString(): Boolean {
    return this.matches(Regex("^[a-zA-ZÀ-ỹ\\s]+$"))

}

fun String.isValidInt(): Boolean {
    return this.matches(Regex("^\\d+$"))
}


//fun String.reverseString(): String {
//    return this.reversed()
//}
//
//fun String.lengthString(): Int {
//    return this.length
//}



