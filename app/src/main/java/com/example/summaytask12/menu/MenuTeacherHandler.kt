package com.example.summaytask12.menu

import com.example.summaytask12.system.DataClass.teachers
import com.example.summaytask12.system.InputHandler
import com.example.summaytask12.system.TeachersManager
import kotlinx.coroutines.runBlocking

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
        println("6.Tìm kiếm giáo viên theo tên")
        println("7.Cập nhật thông tin thường gặp giáo viên")
        println("8. Cập nhật thông tin")
        println("9.Xóa giáo viên")
        println("0. Thoát")

    }
    fun handleSelection() = runBlocking {
        var selection: Int
        do {
            displayMainTeacherMenu()
            print("Nhập lựa chọn: ")
            selection = InputHandler.getMenuSelection()
            println()
            when (selection) {
                1 -> {
                    teachersManager.getAll()
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

                6 -> {
                    teachersManager.getByName(InputHandler.getStringInput("Nhập tên giáo viên:"))
                }

                7 -> {
                    teachersManager.updateTeacher(
                        InputHandler.getIntInput("Nhập id giáo viên:"),
                        InputHandler.getDoubleInput("Nhập lương cần cập nhật"),
                        InputHandler.getStringInput("Nhập môn học:"),
                    )
                }

                8 -> {
                    updateTeacher(teachersManager)
                }

                9 -> {
                    deleteTeacher(teachersManager)
                }

                0 -> {
                    println("Thoát chương trình...")
                }

                else -> {
                    println("Lựa chọn không hợp lệ")
                }
            }
        } while (selection != 0)
    }

    private fun handleTeacherSalary() {
        val id = InputHandler.getIntInput("Nhập ID giáo viên:")
        println(teachersManager.getTeacherSalary(id))
    }

    private suspend fun updateTeacher(teachersManager: TeachersManager) {
        val id = InputHandler.getIntInput("Nhập id giáo viên cần cập nhật")
        val idTeacher= teachersManager.getIdTeacher(id)
        if (id ==idTeacher){
            println("Nhập thông tin giáo viên cần update:")
            val teacher = InputHandler.getTeacherInput()
            teachersManager.update(id, teacher)
        }else{
            println("Không tìm thấy thông tin cần update")
        }
    }

    private suspend fun deleteTeacher(teachersManager: TeachersManager) {
        val id = InputHandler.getIntInput("Nhập id giáo viên cần xóa")
        val idTeacher= teachersManager.getIdTeacher(id)
        if (id ==idTeacher){
            teachersManager.delete(id)
        }else{
            println("Không tìm thấy thông tin cần xóa")
        }
    }

}