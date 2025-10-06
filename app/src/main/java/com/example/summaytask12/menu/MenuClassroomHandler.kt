package com.example.summaytask12.menu

import com.example.summaytask12.enum.StatusSchedule
import com.example.summaytask12.system.CoursesManager
import com.example.summaytask12.data.DataClass
import com.example.summaytask12.util.InputHandler
import com.example.summaytask12.util.OutputHandler
import com.example.summaytask12.system.SchoolManager
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MenuClassroomHandler(
    private val coursesManager: CoursesManager,
    private val schoolManager: SchoolManager,
    private val outputHandler: OutputHandler
) {

    suspend fun handleSelection() {
        var selection: Int
        do {
            outputHandler.displayMenuSchool()
            print("Nhập lựa chọn: ")
            selection = InputHandler.getMenuSelection()
            when (selection) {
                1 -> {
                    handleClassroomManagement()
                }

                2 -> {
                    handleScheduleManagementCouroutine()
                }

                3 -> {
                    handleCancelSchedule()
                }

                4 -> {
                    handleCreateScheduleAsync()
                }

                5 -> {
                    handleCheckEmptyClassrooms()
                }

                6 -> {
                    handleCalculateScheduledRoomsAsync()
                }

                7 -> {
                    coursesManager.getAll()
                }

                8 -> {
                    handleCourseStatistics()
                }

                9 -> {
                    val courseId = InputHandler.getIntInput("Nhập id cần xem số tín")
                    creditsBySubject(courseId)
                }

                10 -> {
                    filterSubjects(coursesManager)
                }

                11 -> {
                    updateCourse(coursesManager)
                }

                12 -> {
                    val id = InputHandler.getIntInput("Id môn học cần xóa")
                    deleteCourse(id, coursesManager)
                }

                13 -> {
                    handleGetAllClassrooms()
                }

                14 -> {
                    handleGetClassroomById()
                }

                15 -> {
                    handleGetClassroomByName()
                }

                16 -> {
                    handleDeleteClassroom()
                }

                17 -> {
                    handleGetAllSchedules()
                }

                18 -> {
                    handleGetScheduleById()
                }

                19 -> {
                    handleGetScheduleByName()
                }

                20 -> {
                    handleDeleteSchedule()
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
        }
    }

    // Sự dụng couroutine
    private suspend fun handleScheduleManagementCouroutine() {
        val schedules = schoolManager.fetchSchedulesAsync()
        if (schedules.isNotEmpty()) {
            println("Danh sách lịch học:")
            schedules.forEachIndexed { index, schedule ->
                println("${index + 1}. ")
                schedule.displaySchedule()
            }
        } else {
            println("Chưa có lịch học nào được tạo")
        }
    }

    private suspend fun handleCalculateScheduledRoomsAsync() {
        val count = schoolManager.calculateScheduledRoomsAsync()
        println("Số phòng đang sử dụng: $count")
    }

    private fun handleCancelSchedule() {
        val id = InputHandler.getIntInput("Nhập ID lịch học cần hủy:")
        schoolManager.cancelSchedule(id)
    }

    private suspend fun handleCreateScheduleAsync() = coroutineScope {
        println("Tạo lịch học mẫu")
        launch {
            schoolManager.createScheduleAsync(DataClass.sampleSchedule2)
        }.join()
        println("Lịch học đã được tạo")
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

    private suspend fun creditsBySubject(courseId: Int) {
        coursesManager.creditsBySubject(courseId) { course ->
            println("Môn học: ${course.courseName} có ${course.credit} tín chỉ.")
        }
    }

    private suspend fun filterSubjects(universityManager: CoursesManager) {
        universityManager.creditFiltering()
    }

    private suspend fun updateCourse(coursesManager: CoursesManager) {
        val id = InputHandler.getIntInput("Id môn học cần cập nhật")
        val idCourse = coursesManager.getIdCourse(id)
        if (idCourse == id) {
            val course = InputHandler.getCourseInput()
            coursesManager.update(id, course)
        } else {
            println("Không tìm thấy thông tin cần nhập ")
        }
    }

    private suspend fun deleteCourse(id: Int, coursesManager: CoursesManager) {
        coursesManager.delete(id)
    }

    // Các phương thức mới cho Classroom
    private suspend fun handleGetAllClassrooms() {
        schoolManager.getAllClassrooms()
    }

    private suspend fun handleGetClassroomById() {
        val id = InputHandler.getIntInput("Nhập ID phòng học cần tìm:")
        val classroom = schoolManager.getClassroomById(id)
        if (classroom != null) {
            println("Thông tin phòng học:")
            println(classroom.displayInfo())
        } else {
            println("Không tìm thấy phòng học với ID: $id")
        }
    }

    private suspend fun handleGetClassroomByName() {
        val name = InputHandler.getStringInput("Nhập tên phòng học cần tìm:")
        val classrooms = schoolManager.getClassroomByName(name)
        if (classrooms.isNotEmpty()) {
            println("Danh sách phòng học tìm thấy:")
            classrooms.forEach { classroom ->
                println(classroom.displayInfo())
            }
        } else {
            println("Không tìm thấy phòng học với tên: $name")
        }
    }

    private suspend fun handleDeleteClassroom() {
        val id = InputHandler.getIntInput("Nhập ID phòng học cần xóa:")
        schoolManager.deleteClassroom(id)
    }

    // Các phương thức mới cho Schedule
    private suspend fun handleGetAllSchedules() {
        schoolManager.getAllSchedules()
    }

    private suspend fun handleGetScheduleById() {
        val id = InputHandler.getIntInput("Nhập ID lịch học cần tìm:")
        val schedule = schoolManager.getScheduleById(id)
        if (schedule != null) {
            println("Thông tin lịch học:")
            schedule.displaySchedule()
        } else {
            println("Không tìm thấy lịch học với ID: $id")
        }
    }

    private suspend fun handleGetScheduleByName() {
        val name = InputHandler.getStringInput("Nhập tên môn học hoặc giáo viên cần tìm:")
        val schedules = schoolManager.getScheduleByName(name)
        if (schedules.isNotEmpty()) {
            println("Danh sách lịch học tìm thấy:")
            schedules.forEachIndexed { index, schedule ->
                println("${index + 1}. ")
                schedule.displaySchedule()
            }
        } else {
            println("Không tìm thấy lịch học với từ khóa: $name")
        }
    }


    private suspend fun handleDeleteSchedule() {
        val id = InputHandler.getIntInput("Nhập ID lịch học cần xóa:")
        schoolManager.deleteSchedule(id)
    }
}