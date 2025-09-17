package com.example.summaytask12.model

data class Enrollment(
    val student: Student,
    val course: Course,
    var grade: Double? = null
) {
    fun displayEnrollment() {
        println("SV: ${student.name}, Môn: ${course.courseName}, Điểm: ${grade ?: "Chưa có"}")
    }
}

