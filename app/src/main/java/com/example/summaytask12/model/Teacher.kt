package com.example.summaytask12.model

import com.example.summaytask12.core.interfaces.Gradable

class Teacher(
    id: Int,
    name: String,
    age: Int,
    var subject: String,
    var salary: Double? = null
) : UniversityMember(id, name, age), Gradable {

    override fun toString(): String {
        return "Teacher(id=$id, name=$name, age=$age, subject =$subject , salary=$salary)"
    }

    private val taughtCourses = mutableListOf<Course>()

    override fun getRole() = "Teacher"

    override fun grade(student: Student, course: Course, grade: Double) {
        println("GV $name chấm cho SV ${student.name} môn ${course.courseName}: $grade điểm")
    }

    override fun provideFeedback(student: Student, course: Course, feedback: String) {
        super.provideFeedback(student, course, feedback)
        println("(Đánh giá chi tiết từ giáo viên)")
    }

    override fun showInfo() {
        println("ID: $id")
        println("Name: $name")
        println("Age: $age")
        println("Subject: $subject")

    }

    fun assignCourse(course: Course) {
        taughtCourses.add(course)
        println("Giáo viên $name được phân công dạy môn ${course.courseName}")
    }

}


