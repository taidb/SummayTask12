package com.example.summaytask12.basic

import com.example.summaytask12.model.Student

fun totalScore(students: List<Student>): Double {
    var totalScore = 0.0
    for (i in students.indices) {
        totalScore += students[i].gpa
    }
    return totalScore
}

fun averageScore(students: List<Student>): Double {
    var averageScore = totalScore(students) / students.size
    return averageScore
}

fun printListStudent(students: List<Student>) {
    for (i in students.indices) {
        var multiLineString = """
    ${students[i].name}
    ${students[i].id}
    ${students[i].age}
""".trimIndent()
        println(multiLineString)
    }
}
