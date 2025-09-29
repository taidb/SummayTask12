package com.example.summaytask12.model

class Course(
    private val courseId: String,
    val courseName: String,
    val credit: Int
) {
    fun displayCourse() {
        println("Course: $courseName Id:  $courseId, Credit: $credit")
    }
}