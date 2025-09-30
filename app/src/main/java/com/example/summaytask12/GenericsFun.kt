package com.example.summaytask12

fun <T> genericsPrint(value: T) {
    println(value)
}

fun <E> addItem(
    items1: MutableList<E>,
    items2: List<E>,
    isDuplicate: (E, E) -> Boolean
) {
    for (item in items2) {
        if (items1.any { isDuplicate(it, item) }) {
            println("$item đã tồn tại!")
        } else {
            items1.add(item)
            println("Đã đăng ký: $item")
        }
    }
}
