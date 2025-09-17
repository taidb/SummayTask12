package com.example.summaytask12.model

import javax.security.auth.Subject

class Teacher(
    id: Int,
    name: String,
    age: Int,
    var subject: String,
    var salary: Double? = null
) : UniversityMember(id, name, age), Gradable {

    override fun getRole() = "Teacher"

    override fun grade(student: Student, course: Course, grade: Double) {
        println("GV $name chấm cho SV ${student.name} môn ${course.courseName}: $grade điểm")
    }
    override fun showInfo() {
        println("ID: $id")
        println("Name: $name")
        println("Age: $age")
        println("Subject: $subject")

    }
    fun updateSalary(newSalary: String) {
        subject = newSalary
    }
}


