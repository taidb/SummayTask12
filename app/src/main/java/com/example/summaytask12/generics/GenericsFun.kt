package com.example.summaytask12.generics

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

fun<E> printInfo(items :MutableList<E>,transform:(E)->String){
    for ((index,item) in items.withIndex()){
        println("${index+1}. ${transform(item)}")
    }
    println("Tổng số: ${items.size} ")
}

// Hàm sự dụng inline : sự dụng cho các lambda nhỏ
inline fun<T> measureExecutionTimeInline(action:() ->T):T{
    val start =System.currentTimeMillis()
    val result =action()
    val end =System.currentTimeMillis()
    println("Thời gian thực thi sự dụng inline: ${end -start} ms")
    return result
}

fun <T> measureExecutionTime(action: () -> T): T {
    val start = System.currentTimeMillis()
    val result = action()
    val end = System.currentTimeMillis()
    println("Thời gian thực thi không sự dụng inline: ${end - start} ms")
    return result
}