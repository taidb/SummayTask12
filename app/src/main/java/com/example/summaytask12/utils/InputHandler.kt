package com.example.summaytask12.utils

import com.example.summaytask12.core.extensions.isValidInt
import com.example.summaytask12.core.extensions.isValidString
import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Student
import com.example.summaytask12.model.Teacher


object InputHandler {
    fun getMenuSelection(): Int {
        return readln().toIntOrNull() ?: 0
    }

    fun getIntInput(prompt: String): Int {
        while (true) {
            print(prompt)
            val input = readln()

            if (input.isValidInt()) {
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


    fun getStringInput(prompt: String): String {
        while (true) {
            println(prompt)
            val inputString = readln()
            if (inputString.isValidString()) {
                return inputString
            } else {
                println("Vui lòng nhập lại thông tin")
            }
        }
    }
    private fun filterGPA():Double{
            val gpa: Double
            while (true) {
                val inputDouble= getDoubleInput("Nhập GPA trong khoảng 0.0 đến 4.0")
                if (inputDouble in 0.0..4.0) {
                    gpa = inputDouble
                    break
                } else {
                    println("GPA không hợp lệ. Vui lòng nhập một số trong khoảng từ 0.0 đến 4.0.")
                }
            }
            return gpa
    }

    private fun filterAge(): Int {
        val age: Int
        while (true) {
            val inputInt= getIntInput("Nhập tuổi lớn hơn 0")
            if (inputInt >= 1) {
                age = inputInt
                break
            } else {
                println("Nhập tuổi lớn hơn 0")
            }
        }
        return age
    }

    fun getStudentInput(): Student {
        val id = getIntInput("Nhập ID:")
        val name = getStringInput("Nhập tên:")
        val age = getIntInput("Nhập tuổi:")
        val gpa= filterGPA()
        val address = getStringInput("Nhập địa chỉ:")
        return Student(id, name, age, gpa, address)
    }

    fun getTeacherInput(): Teacher {
        val id = getIntInput("Nhập ID:")
        val name = getStringInput("Nhập tên:")
        val age= filterAge()
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