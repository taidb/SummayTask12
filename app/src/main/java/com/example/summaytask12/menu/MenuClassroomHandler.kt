package com.example.summaytask12.menu

import com.example.summaytask12.enum.StatusSchedule
import com.example.summaytask12.system.CoursesManager
import com.example.summaytask12.system.DataClass
import com.example.summaytask12.system.InputHandler
import com.example.summaytask12.system.SchoolManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MenuClassroomHandler(
    private val coursesManager: CoursesManager,
    private val schoolManager: SchoolManager,
) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    private fun displayMenu() {
        println("\n=== HỆ THỐNG QUẢN LÝ TRƯỜNG HỌC ===")
        println("1. Quản lý Phòng học")
        println("2. Quản lý Lịch học")
        println("3. Hủy đăng kí phòng học")
        println("4. Đăng kí phòng học")
        println("5. Kiểm tra lớp còn trống")
        println("6. Số phòng đang sử dụng")
        println("7. In danh sách môn học")
        println("8. Thống kê môn học")
        println("9. Số tín chỉ trên mỗi môn học")
        println("10. In môn học có tín chỉ lớn hơn 3")
        println("11. Cập nhật thông tin môn học")
        println("12. Xóa môn hoc theo Id")

        println("0. Thoát")
    }

    fun handleSelection() {
        var selection: Int
        do {
            displayMenu()
            print("Nhập lựa chọn: ")
            selection = InputHandler.getMenuSelection()
            when (selection) {
                1 -> {
                    handleClassroomManagement()
                }

                2 -> {
                    scope.launch { handleScheduleManagementCouroutine() }
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
                    scope.launch { handleCalculateScheduledRoomsAsync() }
                }

                7 -> {
                    scope.launch { coursesManager.getAll() }
                }

                8 -> {
                    handleCourseStatistics()
                }

                9 -> {
                    val courseId = InputHandler.getIntInput("Nhập id cần xem số tín")
                    scope.launch { creditsBySubject(courseId) }
                }

                10 -> {
                    scope.launch { filterSubjects(coursesManager) }
                }

                11 -> {
                    val id = InputHandler.getIntInput("Id môn học cần cập nhật")
                    scope.launch { updateCourse(id, coursesManager) }
                }

                12 -> {
                    val id = InputHandler.getIntInput("Id môn học cần xóa")
                    scope.launch { deleteCourse(id, coursesManager) }
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

    // không sự dụng couroutine
//    private fun handleScheduleManagement() {
//        printListSchedules(schoolManager)
//    }
//
//    private fun printListSchedules(universityManager: SchoolManager) {
//        if (universityManager.getSchedules().isNotEmpty()) {
//            println("Danh sách lịch học:")
//            universityManager.getSchedules().forEachIndexed { index, schedule ->
//                println("${index + 1}. ")
//                schedule.displaySchedule()
//            }
//        } else {
//            println("Chưa có lịch học nào được tạo")
//        }
//    }

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
        println("🏫 Số phòng đang sử dụng: $count")
    }

    private fun handleCancelSchedule() {
        val id = InputHandler.getIntInput("Nhập ID lịch học cần hủy:")
        schoolManager.cancelSchedule(id)
    }

//    private fun handleCreateSchedule() {
//        schoolManager.createSchedule(DataClass.sampleSchedule2)
//        println("Đã tạo lịch học mẫu")
//    }

    private fun handleCreateScheduleAsync() = runBlocking {
        println("Tạo lịch học mẫu")
        launch {
            schoolManager.createScheduleAsync(this, DataClass.sampleSchedule2)
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
            println("➡️ Môn học: ${course.courseName} có ${course.credit} tín chỉ.")
        }
    }

    private suspend fun filterSubjects(universityManager: CoursesManager) {
        universityManager.creditFiltering()
    }

    private suspend fun updateCourse(id: Int, coursesManager: CoursesManager) {
        val course = InputHandler.getCourseInput()
        coursesManager.update(id, course)
    }

    private suspend fun deleteCourse(id: Int, coursesManager: CoursesManager) {
        coursesManager.delete(id)

    }
}

