package com.example.summaytask12.interfaces

import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Student

interface Gradable {
    fun grade(student: Student, course: Course, grade: Double)
}
