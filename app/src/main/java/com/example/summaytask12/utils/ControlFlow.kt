package com.example.summaytask12.utils

import com.example.summaytask12.model.Student

//if else thông thường
fun conditional(students: List<Student>) {
    for (i in 0 until students.size) {
        for (j in i + 1 until students.size) {
            if (students[i].equals(students[j])) {
                println("Sinh viên ${students[i].name} trùng với sinh viên ${students[j].name}")
            }
        }
    }
}

//when
fun whenDemo(students: List<Student>) {
    for (student in students) {
        when (student.gpa) {
            in 0.0..1.5 -> println("Sinh viên ${student.name} xếp loại yếu")
            in 1.6..2.0 -> println("Sinh viên ${student.name} xếp loại dưới trung bình")
            in 2.1..3.0 -> println("Sinh viên ${student.name} xếp loại trung bình")
            in 3.1..3.5 -> println("Sinh viên ${student.name} xếp loại khá")
            in 3.6..4.0 -> println("Sinh viên ${student.name} xếp loại giỏi")
            else -> println("Điểm GPA không hợp lệ")
        }
    }
}


//for
fun demoFor(students: List<Student>) {
    for (student in students) {
        println(student.name)
    }
//    for (i in students.indices) {
//        println(students[i].name)
//    }
//    for ((index, value) in students.withIndex()) {
//        println("Index: $index, Value: $value")
//    }
//    students.forEach { println(it.name) }
//    students.forEachIndexed { index, student -> println("Index: $index, Value: $student") }
//
//    for (i in students.indices step 2) {
//        println(students[i].name)
//    }
//    for (i in 0..students.size) {
//        println(students[i])
//    }
//    for (i in 0 until students.size) {
//        println(students[i])
//    }
//    for (i in students.size - 1 downTo 0 step 2) {
//        println(students[i])
//    }

}

//While
fun whileDemo(students: List<Student>) {
    println("Danh sách sinh viên:")
    var i = 0
    while (i < students.size) {
        println(students[i])
        i++
    }
}

//do While
fun doWhileDemo(students: List<Student>) {
    var i = 0
    do {
        println(students[i])
        i++
    } while (i < students.size)
}

//break
fun breakDemo(students: List<Student>) {
    println("In danh sách đến sinh viên có tên là Nguyễn Văn Dũng:")
    for (i in students.indices) {
        if (students[i].name.equals("Nguyễn Văn Dũng")) {
            break
        }
        println(students[i].name)
    }
}

//continue
fun continueDemo(students: List<Student>) {
    println("In danh sách sinh viên xếp loại giỏi và xuất sắc")
    for (i in students.indices) {
        if (students[i].gpa >= 3.2) {
            continue
        }
        println(students[i].name)
    }
}