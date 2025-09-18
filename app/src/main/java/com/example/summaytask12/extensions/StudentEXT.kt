package com.example.summaytask12.extensions

import com.example.summaytask12.model.Student


// Extension Function cho Student
fun Student.isScholarship(): String {
    return if (gpa >= 3.5) "Scholarship" else "No Scholarship"
}

fun Student.calculateTuition(creditPrice: Double): Double {
    val totalCredits = this.getEnrolledCourses().sumOf { it.credit }
    return totalCredits * creditPrice
}

fun Student.getTotalCredits(): Int {
    return this.getEnrolledCourses().sumOf { it.credit }
}