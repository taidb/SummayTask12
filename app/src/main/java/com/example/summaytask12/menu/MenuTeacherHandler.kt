package com.example.summaytask12.menu

import com.example.summaytask12.system.DataClass.teachers
import com.example.summaytask12.system.InputHandler
import com.example.summaytask12.system.TeachersManager

class MenuTeacherHandler(
    private val teachersManager: TeachersManager,
) {
    private fun displayMainTeacherMenu() {
        println("\n=== HỆ THỐNG QUẢN LÝ GIÁO VIÊN TRƯỜNG HỌC ===")
        println("1. In danh sách giáo viên")
        println("2. Tiền lương từng giáo viên")
        println("3. Lương giáo viên theo Id")
        println("4. Môn học giáo viên dạy")
        println("5. Kiểm tra đại học")
        println("6.Tìm kiếm giáo viên theo Id")
        println("0. Thoát")

    }

    fun handleSelection() {
        var selection :Int
        do {

            displayMainTeacherMenu()
            print("Nhập lựa chọn: ")
            selection = InputHandler.getMenuSelection()
            when (selection) {
                1 -> {
                    teachersManager.displayAllTeachers()
                }

                2 -> {
                    teachersManager.calculateAnnualSalaryTeacher(teachers)
                }

                3 -> {
                    handleTeacherSalary()
                }

                4 -> {
                    println(teachersManager.getgroupsOfTeachersBySubject())
                }

                5 -> {
                    teachersManager.performUniversityAudit()
                }

                0 -> {
                    println("Thoát chương trình...")
                }

                else -> {
                    println("Lựa chọn không hợp lệ")
                }
            }
        }while (selection!=0)
    }

    private fun handleTeacherSalary() {
        val id = InputHandler.getIntInput("Nhập ID giáo viên:")
        println(teachersManager.getTeacherSalary(id))
    }

}