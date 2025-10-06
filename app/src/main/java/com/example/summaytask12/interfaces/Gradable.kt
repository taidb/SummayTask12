package com.example.summaytask12.interfaces

import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Student

interface Gradable {
    fun grade(student: Student, course: Course, grade: Double)
    fun provideFeedback(student: Student, course: Course, feedback: String) {
        println("GV nhận xét cho SV ${student.name} môn ${course.courseName}: $feedback")
    }
}
