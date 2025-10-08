package com.example.summaytask12.core.generics

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
            genericsPrint("$item đã tồn tại!")
        } else {
            items1.add(item)
            genericsPrint("Đã đăng ký: $item")
        }
    }
}

fun <E> printInfo(items: MutableList<E>, transform: (E) -> String) {
    for ((index, item) in items.withIndex()) {
        genericsPrint("${index + 1}. ${transform(item)}")
    }
    genericsPrint("Tổng số: ${items.size} ")
}

fun <T, E> updateItem(items: MutableList<E>, id: T, item: E, idSelector: (E) -> T) {
    val index = items.indexOfFirst { idSelector(it) == id }
    if (index != -1) {
        items[index] = item
        genericsPrint("Đã cập nhật thông tin $item")
    } else {
        genericsPrint("Không tìm thấy $item để cập nhật.")
    }
}

fun<T,E> deleteItem(items: MutableList<E>, id: T,idSelector: (E) -> T) {
    val item = items.find { idSelector(it) == id  }
    if (item != null) {
        items.remove(item)
        genericsPrint("Đã xóa $id")
    }else{
        genericsPrint("Không tìm thấy $id để xóa.")
    }

}

// Hàm sự dụng inline : sự dụng cho các lambda nhỏ
inline fun <T> measureExecutionTimeInline(action: () -> T): T {
    val start = System.currentTimeMillis()
    val result = action()
    val end = System.currentTimeMillis()
    println("Thời gian thực thi sự dụng inline: ${end - start} ms")
    return result
}

fun <T> measureExecutionTime(action: () -> T): T {
    val start = System.currentTimeMillis()
    val result = action()
    val end = System.currentTimeMillis()
    println("Thời gian thực thi không sự dụng inline: ${end - start} ms")
    return result
}