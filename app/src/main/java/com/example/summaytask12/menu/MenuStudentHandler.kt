package com.example.summaytask12.menu

import com.example.summaytask12.enum.AgeRange
import com.example.summaytask12.system.DataClass.students
import com.example.summaytask12.system.InputHandler
import com.example.summaytask12.system.StudentsManager
import kotlinx.coroutines.runBlocking

class MenuStudentHandler(
    private val studentsManager: StudentsManager,
) {

    private fun displayMainStudentMenu() {
        println("\n=== HỆ THỐNG QUẢN LÝ SINH VIÊN TRƯỜNG HỌC ===")
        println("1. Thêm sinh viên")
        println("2. In danh sách sinh viên")
        println("3. Tạo báo cáo sinh viên cần tìm kiếm")
        println("4. Tổng học phí sinh viên")
        println("5. Thống kê sinh viên theo độ tuổi")
        println("6. Điểm GPA trung bình của sinh viên")
        println("7. Thêm sinh viên mẫu")
        println("8. Kiểm tra trùng ID")
        println("9. Danh sách xếp loại")
        println("10. Sinh viên đăng kí môn học")
        println("11. Tìm kiếm sinh viên theo địa chỉ")
        println("12. Cập nhật sinh viên")
        println("13. Tìm kiếm sinh viên theo tên")
        println("14. Điểm trung bình tất cả sinh viên")
        println("15.Sinh viên đỗ tốt nghiệp")
        println("16.Xóa sinh viên theo Id")
        println("17.Danh sách trạng thái sinh viên")
        println("18.5 Sinh viên đạt học bổng")
        println("19.Tổng số tỉn chỉ sinh viên")
        println("20.Kiểm tra tình trạng sinh viên : Học bổng ,Cảnh cáo")
        println("0. Thoát")
    }

    fun handleSelection() {
        var selection: Int
        do {

            displayMainStudentMenu()
            print("Nhập lựa chọn: ")
            selection = InputHandler.getMenuSelection()
            when (selection) {
                1 -> {
                    handleAddStudent()
                }

                2 ->{
                    studentsManager.displayAllStudents()
                }

                3 -> {
                    handleGenerateStudentReport()
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

                20 ->{
                    studentsManager.scholarshipStudents(students)
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

    private fun handleAddStudent() {
        val student = InputHandler.getStudentInput()
        studentsManager.addStudent(student)
    }

    private fun handleGenerateStudentReport() {
        val id = InputHandler.getIntInput("Nhập ID cần tìm kiếm:")
        generateStudentReport(id, studentsManager)
    }

    private fun handleCalculateTuition() {
        val creditPrice = InputHandler.getDoubleInput("Nhập số tiền học phí:")
        studentsManager.calculateTuitionStudent(students, creditPrice)
    }

    private fun handleRemoveStudent() {
        val id = InputHandler.getIntInput("Nhập ID cần xóa:")
        studentsManager.cleanStudent(id)
    }

    private fun handleGraduationEligibility() {
        runBlocking {
            val result = studentsManager.processGraduationAsync(students).toString()
            println(result)
        }
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

    private fun handleUpdateStudent() {
        val id = InputHandler.getIntInput("Nhập ID cần cập nhật:")
        println("Nhập thông tin mới:")
        val student = InputHandler.getStudentInput()
        studentsManager.updateStudent(id, student)
    }

    private fun handleNameSearch() {
        val name = InputHandler.getStringInput("Nhập tên cần tìm kiếm:")
        println(studentsManager.findStudentsByName(name))
    }

    // Các hàm utility giữ nguyên
    private fun generateStudentReport(studentId: Int, studentsManager: StudentsManager) {
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