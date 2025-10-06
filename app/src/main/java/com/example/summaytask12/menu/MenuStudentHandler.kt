package com.example.summaytask12.menu

import com.example.summaytask12.enum.AgeRange
import com.example.summaytask12.system.DataClass.students
import com.example.summaytask12.system.InputHandler
import com.example.summaytask12.system.OutputHandler
import com.example.summaytask12.system.StudentsManager

class MenuStudentHandler(
    private val studentsManager: StudentsManager,
    private val outputHandler: OutputHandler
) {
   suspend fun handleSelection() {
        var selection: Int
        do {

            outputHandler.displayMainStudentMenu()
            print("Nhập lựa chọn: ")
            selection = InputHandler.getMenuSelection()
            when (selection) {
                1 -> {
                    handleAddStudent()
                }

                2 -> {
                    studentsManager.getAll()
                }

                3 -> {
                    handleGenerateStudentReport(studentsManager)
                }

                4 -> {
                    handleCalculateTuition()
                }

                5 -> {
                    handleAgeRangeStatistics()
                }

                6 -> {
                    handleAverageGPA()
                }

                7 -> {
                    studentsManager.createSampleStudent()
                }

                8 -> {
                    studentsManager.duplicateTest(students)
                }

                9 -> {
                    studentsManager.conditionClassification(students)
                }

                10 -> {
                    students.forEach { it.listCourses() }
                }

                11 -> {
                    handleAddressSearch()
                }

                12 -> {
                    handleUpdateStudent()
                }

                13 -> {
                    handleNameSearch()
                }

                14 -> {
                    handleAverageAllGPA()
                }

                15 -> {
                    handleGraduationEligibility()
                }

                16 -> {
                    handleRemoveStudent()
                }

                17 -> {
                    studentsManager.processGraduation()
                }

                18 -> {
                    studentsManager.getTopPerformingStudents()
                }

                19 -> {
                    studentsManager.getTotalCreditsStudent(students)
                }

                20 -> {
                    studentsManager.scholarshipStudents(students)
                }

                21 -> {
                    println(studentsManager.filterStudents(students) { it.gpa >= 3.5 })
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

    private suspend fun handleAddStudent() {
        val student = InputHandler.getStudentInput()
        studentsManager.addStudent(student)
    }

    private suspend fun handleGenerateStudentReport(studentsManager: StudentsManager) {
        val id = InputHandler.getIntInput("Nhập ID cần tìm kiếm:")
        val idStudent = studentsManager.getIdStudent(id)
        if (idStudent == id) {
            generateStudentReport(id, studentsManager)
        } else {
            println("Không tìm thấy id")
        }
    }

    private fun handleCalculateTuition() {
        val creditPrice = InputHandler.getDoubleInput("Nhập số tiền học phí:")
        studentsManager.calculateTuitionStudent(students, creditPrice)
    }

    private suspend fun handleRemoveStudent() {
        val id = InputHandler.getIntInput("Nhập ID cần xóa:")
        val idStudent = studentsManager.getIdStudent(id)
        if (idStudent == id) {
            studentsManager.delete(id)
        } else {
            println("Không tìm thấy id")
        }
    }

    private suspend fun handleGraduationEligibility() {
        val result = studentsManager.processGraduationAsync(students).toString()
        println(result)

    }

    private fun handleAgeRangeStatistics() {
        getStudentCountByAgeRange(studentsManager)
    }

    private fun handleAverageGPA() {
        println("Điểm GPA trung bình: ${studentsManager.getCalculateAverageGPA()}")
    }

    private fun handleAverageAllGPA() {
        getCalculateAverageGPA(studentsManager)
    }

    private fun handleAddressSearch() {
        val address = InputHandler.getStringInput("Nhập địa chỉ cần tìm kiếm:")
        studentsManager.addressCheck(students, address)
    }

    private suspend fun handleUpdateStudent() {
        val id = InputHandler.getIntInput("Nhập ID cần cập nhật:")
        println("Nhập thông tin mới:")
        val student = InputHandler.getStudentInput()
        studentsManager.update(id, student)
    }

    private suspend fun handleNameSearch() {
        val name = InputHandler.getStringInput("Nhập tên cần tìm kiếm:")
        println(studentsManager.getByName(name))
    }

    // Các hàm utility giữ nguyên
    private suspend fun generateStudentReport(studentId: Int, studentsManager: StudentsManager) {
        println("Thông tin cần tìm: ${studentsManager.generateStudentReport(studentId)}")
    }

    private fun getStudentCountByAgeRange(studentsManager: StudentsManager) {
        println("Số lượng sinh viên nhỏ hơn 18 tuổi: ${studentsManager.getStudentCountByAgeRange()[AgeRange.YOUNG]}")
        println("Số lượng sinh viên từ 18 đến 20 tuổi: ${studentsManager.getStudentCountByAgeRange()[AgeRange.ADULT]}")
        println("Số lượng sinh viên trên 20 tuổi: ${studentsManager.getStudentCountByAgeRange()[AgeRange.MATURE]}")
    }

    private fun getCalculateAverageGPA(studentsManager: StudentsManager) {
        println("Điểm GPA trung bình của tất cả sinh viên: ${studentsManager.getCalculateAverageGPA()}")
    }

}