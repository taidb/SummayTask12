package com.example.summaytask12.model

import android.location.Address

class Student(
    id: Int,
    name: String,
    age: Int,
    var gpa: Double,
    enroledCourses: MutableList<Course>,
) : UniversityMember(id, name, age), Enrollable {

    private val enrolledCourses = mutableListOf<Course>()

    override fun getRole() = "Student"

    override fun enrollStudent(course: Course) {
        enrolledCourses.add(course)
        println("$name đã đăng ký môn ${course.courseName}")
    }

    override fun listCourses() {
        println("Các môn $name đã đăng ký:")
        if (enrolledCourses.isEmpty()) {
            println("Chưa đăng ký môn nào")
        } else {
            enrolledCourses.forEach { println("- ${it.courseName}") }
        }
    }


}

// Extension Function
fun Student.isScholarship(): String {
    return if (gpa >= 3.5) "Scholarship" else "No Scholarship"
}

fun String.capitalizeFirst(): String {
    return if (this.isNotEmpty()) this[0].uppercase() + this.substring(1) else this
}

fun String.reverseString(): String {
    return this.reversed()
}

fun String.lengthString(): Int {
    return this.length
}




