package com.example.summaytask12.menu

import com.example.summaytask12.enum.StatusSchedule
import com.example.summaytask12.system.CoursesManager
import com.example.summaytask12.system.DataClass
import com.example.summaytask12.system.InputHandler
import com.example.summaytask12.system.SchoolManager

class MenuClassroomHandler(
    private val coursesManager: CoursesManager,
    private val schoolManager: SchoolManager,
) {

    private fun displayMenu() {
        println("\n=== HỆ THỐNG QUẢN LÝ TRƯỜNG HỌC ===")
        println("1. Quản lý Phòng học")
        println("2. Quản lý Lịch học")
        println("3. Hủy đăng kí phòng học")
        println("4. Đăng kí phòng học")
        println("5. Kiểm tra lớp còn trống")
        println("6. In danh sách môn học")
        println("7. Thống kê môn học")
        println("0. Thoát")
        print("Nhập lựa chọn: ")
    }

    fun handleSelection() {
        var selection: Int
        do {
            displayMenu()
            selection = InputHandler.getMenuSelection()
            when (selection) {
                1 -> {
                    handleClassroomManagement()
                }

                2 -> {
                    handleScheduleManagement()
                }

                3 -> {
                    handleCancelSchedule()
                }

                4 -> {
                    handleCreateSchedule()
                }

                5 -> {
                    handleCheckEmptyClassrooms()
                }

                6 -> {
                    coursesManager.displayAllCourses()
                }

                7 -> {
                    handleCourseStatistics()
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

    private fun handleCourseStatistics() {
        getCourseStatistics(coursesManager)
    }


    private fun getCourseStatistics(coursesManager: CoursesManager) {
        println("In ra thống kê môn học: ${coursesManager.getCourseStatistics().entries}")
        println("Tổng số môn học: ${coursesManager.getCourseStatistics()["total_courses"]}")
        println("Tổng số tín chỉ: ${coursesManager.getCourseStatistics().values}")
        println(
            "Số môn học lớn hơn 3 tín: ${
                coursesManager.getCourseStatistics()
                    .getOrDefault("advanced_courses", "Không có môn nào")
            }"
        )
        println("Trung bình số tín chỉ: ${coursesManager.getCourseStatistics()["average_credits"]}")
    }

    private fun handleClassroomManagement() {
        printListClassroom(schoolManager)
    }


    private fun printListClassroom(schoolManager: SchoolManager) {
        println("Quản lí phòng học")
        println("Danh sách phòng học:")
        val getClassrooms = schoolManager.getClassrooms()
        getClassrooms.forEachIndexed { _, room ->
            println(room.displayInfo())
//        println("${index + 1}. Phòng ${room.roomNumber} (Sức chứa: ${room.capacity}) - Tiện nghi: ${room.facilities.joinToString()} - Trạng thái: ${room.status}")
        }
        println("Tổng số phòng: ${getClassrooms.size}")
    }

    private fun handleScheduleManagement() {
        printListSchedules(schoolManager)
    }

    private fun printListSchedules(universityManager: SchoolManager) {
        if (universityManager.getSchedules().isNotEmpty()) {
            println("Danh sách lịch học:")
            universityManager.getSchedules().forEachIndexed { index, schedule ->
                println("${index + 1}. ")
                schedule.displaySchedule()
            }
        } else {
            println("Chưa có lịch học nào được tạo")
        }
    }

    private fun handleCancelSchedule() {
        val id = InputHandler.getIntInput("Nhập ID lịch học cần hủy:")
        schoolManager.cancelSchedule(id)
    }

    private fun handleCreateSchedule() {
        schoolManager.createSchedule(DataClass.sampleSchedule2)
        println("Đã tạo lịch học mẫu")
    }

    private fun handleCheckEmptyClassrooms() {
        getClassroomsIsEmpty(schoolManager)
    }

    private fun getClassroomsIsEmpty(universityManager: SchoolManager) {
        universityManager.getClassroomsIsEmpty(
            condition = { cl -> cl.status == StatusSchedule.DRUM },
            action = { cl -> println("Phòng TRỐNG: ${cl.roomNumber}, Sức chứa: ${cl.capacity}, Tiện nghi: ${cl.facilities.joinToString()}, Trạng thái: ${cl.status}") }
        )
        universityManager.getClassroomsIsEmpty(
            condition = { cl -> cl.status == StatusSchedule.CANCELED },
            action = { cl -> println("Phòng ĐÃ HỦY LỊCH: ${cl.roomNumber}, Sức chứa: ${cl.capacity}, Tiện nghi: ${cl.facilities.joinToString()}, Trạng thái: ${cl.status}") }
        )
    }
}

