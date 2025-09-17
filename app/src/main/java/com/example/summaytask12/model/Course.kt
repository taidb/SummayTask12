package com.example.summaytask12.model

class Course(
    val courseId: String,
    val courseName: String,
    val credit: Int
) {
    fun displayCourse() {
        println("Course: $courseName ($courseId), Credit: $credit")
    }
}