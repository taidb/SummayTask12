package com.example.summaytask12.model

import com.example.summaytask12.interfaces.Enrollable

class Student(
    id: Int,
    name: String,
    age: Int,
    var gpa: Double,
) : UniversityMember(id, name, age), Enrollable {

    private val enrolledCourses = mutableListOf<Course>()

    init {
        println("Họ tên: $name, Tuổi: $age , Mã sinh viên: $id")
    }

    //Vì trong class nó ko tự sinh ra toString nên cần override để nó có thể in ra sinh viên theo mẫu :
    override fun toString(): String {
        return "Student(id=$id, name=$name, age=$age, gpa=$gpa)"
    }

    var address:String=""
    constructor(id: Int, name: String, age: Int, gpa: Double,address: String) : this(id, name, age, gpa){
        this.address=address
    }

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
    fun getEnrolledCourses(): List<Course> = enrolledCourses.toList()


    override fun showInfo() {
        super.showInfo()
        println("GPA: $gpa")
        println("Số môn đã đăng ký: ${enrolledCourses.size}")
    }

}


