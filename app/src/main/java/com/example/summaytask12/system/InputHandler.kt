package com.example.summaytask12.system

import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Student
import com.example.summaytask12.model.Teacher


object InputHandler {
    fun getMenuSelection(): Int {
        return readln().toIntOrNull() ?: 0
    }

    private fun isValidInt(input: String): Boolean {
        return input.matches(Regex("^\\d+$"))
    }

    fun getIntInput(prompt: String): Int {
        while (true) {
            print("$prompt: ")
            val input = readln()

            if (isValidInt(input)) {
                return input.toInt()
            } else {
                println("Vui lòng nhập đúng định dạng số nguyên (chỉ chứa các chữ số)!")
            }
        }
    }

    fun getDoubleInput(prompt: String): Double {
        println(prompt)
        return readln().toDoubleOrNull() ?: 0.0
    }

    private fun isValidString(input: String): Boolean {
        return input.matches(Regex("^[a-zA-ZÀ-ỹ\\s]+$"))
    }

    fun getStringInput(prompt: String): String {
        while (true) {
            println(prompt)
            val inputString = readln()
            if (isValidString(inputString)) {
                return inputString
            } else {
                println("Vui lòng nhập lại thông tin")
            }
        }
    }

    fun getStudentInput(): Student {
        val id = getIntInput("Nhập ID:")
        val name = getStringInput("Nhập tên:")
        val age = getIntInput("Nhập tuổi:")
        val gpa: Double
        while (true) {
            val inputGpa = getDoubleInput("Nhập GPA (từ 0.0 đến 4.0)")
            if (inputGpa in 0.0..4.0) {
                gpa = inputGpa
                break
            } else {
                println("GPA không hợp lệ. Vui lòng nhập một số trong khoảng từ 0.0 đến 4.0.")
            }
        }
        val address = getStringInput("Nhập địa chỉ:")
        return Student(id, name, age, gpa, address)
    }

    fun getTeacherInput(): Teacher {
        val id = getIntInput("Nhập ID:")
        val name = getStringInput("Nhập tên:")
        val age: Int
        while (true) {
            val inputAge = getIntInput("Nhập tuổi lớn hơn 0")
            if (inputAge > 0) {
                age = inputAge
                break
            } else {
                println("Nhập tuổi lớn hơn 0")
            }

        }
        val subject = getStringInput("Nhập môn học giảng dạy:")
        val salary = getDoubleInput("Nhập lương giáo viên:")
        return Teacher(id, name, age, subject, salary)
    }

    fun getCourseInput(): Course {
        val id = getIntInput("Nhập ID:")
        val name = getStringInput("Nhập tên môn học:")
        val credit = getIntInput("Nhập số tín:")
        return Course(id, name, credit)
    }

}