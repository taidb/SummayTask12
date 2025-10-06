package com.example.summaytask12.system

import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Student
import com.example.summaytask12.model.Teacher


object InputHandler {
    fun getMenuSelection(): Int {
        return readln().toIntOrNull() ?: 0
    }

    fun getIntInput(prompt: String): Int {
        println(prompt)
        return readln().toIntOrNull() ?: 0
    }

    fun getDoubleInput(prompt: String): Double {
        println(prompt)
        return readln().toDoubleOrNull() ?: 0.0
    }

    private fun isValidString(input: String): Boolean {
        return input.matches(Regex("^[a-zA-ZÀ-ỹ\\s]+$"))
    }

    fun getStringInput(prompt: String): String {
        println(prompt)
        return if (isValidString(readln())) {
            readln()
        } else {
            println("Vui lòng nhập lại thông tin")
            getStringInput(prompt)
        }
    }

    fun getStudentInput(): Student {
        println("Nhập ID:")
        val id = getIntInput("")
        println("Nhập tên:")
        val name = getStringInput("")
        println("Nhập tuổi:")
        val age = getIntInput("")
        println("Nhập GPA:")
        val gpa = getDoubleInput("")
        println("Nhập địa chỉ:")
        val address = getStringInput("")
        return Student(id, name, age, gpa, address)
    }

    fun getTeacherInput(): Teacher {
        println("Nhập ID:")
        val id = getIntInput("")
        println("Nhập tên:")
        val name = getStringInput("")
        println("Nhập tuổi:")
        val age = getIntInput("")
        println("Nhập môn học giảng dạy:")
        val subject = getStringInput("")
        println("Nhập lương giáo viên:")
        val salary = getDoubleInput("")
        return Teacher(id, name, age, subject, salary)
    }

    fun getCourseInput(): Course {
        println("Nhập ID:")
        val id = getIntInput("")
        println("Nhập tên môn học:")
        val name = getStringInput("")
        println("Nhập số tín:")
        val credit = getIntInput("")
        return Course(id, name, credit)
    }

}