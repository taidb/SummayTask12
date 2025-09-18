package com.example.summaytask12.extensions

import com.example.summaytask12.model.Student
import kotlin.collections.List

// Extension function cho List<Student>
fun List<Student>.filterEligibleForGraduation(minGPA: Double): List<Student> {
    return this.filter { it.gpa >= minGPA && it.age > 18 }
}

fun List<Student>.sortByGPADescending(): List<Student> {
    return this.sortedByDescending { it.gpa }
}

