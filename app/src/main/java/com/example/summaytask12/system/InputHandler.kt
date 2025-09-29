package com.example.summaytask12.system

import com.example.summaytask12.model.Student


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

    fun getStringInput(prompt: String): String {
        println(prompt)
        return readln()
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
}