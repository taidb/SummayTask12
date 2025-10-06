package com.example.summaytask12

import com.example.summaytask12.menu.MenuClassroomHandler
import com.example.summaytask12.menu.MenuStudentHandler
import com.example.summaytask12.menu.MenuTeacherHandler
import com.example.summaytask12.system.CoursesManager
import com.example.summaytask12.system.DataClass
import com.example.summaytask12.system.InputHandler
import com.example.summaytask12.system.SchoolManager
import com.example.summaytask12.system.StudentsManager
import com.example.summaytask12.system.TeachersManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main() {
    val coursesManager = CoursesManager()
    val schoolManager = SchoolManager()
    val studentsManager = StudentsManager()
    val teachersManager = TeachersManager()

    val menuStudentHandler = MenuStudentHandler(studentsManager)
    val menuTeacherHandler = MenuTeacherHandler(teachersManager)
    val menuClassroomsHandler =
        MenuClassroomHandler(coursesManager, schoolManager)
    val scope = CoroutineScope(Dispatchers.Default)
    // Khởi tạo dữ liệu
    scope.launch { createCourses(coursesManager) }
    scope.launch { hireTeacher(teachersManager) }
    scope.launch { registerStudent(studentsManager) }
    addClassrooms(schoolManager)
    createSchedules(schoolManager)
    createData()

    var selection: Int
    do {
        println("1.HỆ THỐNG QUẢN LÝ TRƯỜNG HỌC")
        println("2.HỆ THỐNG QUẢN LÝ GIÁO VIÊN TRƯỜNG HỌC")
        println("3.HỆ THỐNG QUẢN LÝ SINH VIÊN TRƯỜNG HỌC")
        println("0. Thoát")
        print("Nhập lựa chọn: ")
        selection = InputHandler.getMenuSelection()
        when (selection) {
            1 -> menuClassroomsHandler.handleSelection()
            2 -> menuTeacherHandler.handleSelection()
            3 -> menuStudentHandler.handleSelection()
            0 -> println("Thoát chương trình...")
            else -> println("Lựa chọn không hợp lệ")
        }
    } while (selection != 0)
}

fun createData() {
    // Sinh viên đăng ký môn học
    DataClass.students[0].enrollStudent(DataClass.courses[0])
    DataClass.students[0].enrollStudent(DataClass.courses[1])
    DataClass.students[1].enrollStudent(DataClass.courses[2])
    DataClass.students[1].enrollStudent(DataClass.courses[3])

    // Giáo viên được phân công môn học
    DataClass.teachers[0].assignCourse(DataClass.courses[0])
    DataClass.teachers[1].assignCourse(DataClass.courses[1])
    DataClass.teachers[2].assignCourse(DataClass.courses[3])

    // Giáo viên chấm điểm
    DataClass.teachers[0].grade(DataClass.students[0], DataClass.courses[0], 8.5)
    DataClass.teachers[1].grade(DataClass.students[0], DataClass.courses[1], 7.8)
    DataClass.teachers[2].grade(DataClass.students[1], DataClass.courses[2], 6.5)
    DataClass.teachers[1].grade(DataClass.students[1], DataClass.courses[3], 5.5)

    DataClass.teachers[0].provideFeedback(
        DataClass.students[0],
        DataClass.courses[0],
        "Sinh viên tiếp thu bài tốt"
    )
    DataClass.teachers[0].provideFeedback(
        DataClass.students[0],
        DataClass.courses[1],
        "Sinh viên tiếp thu bài tốt"
    )
    DataClass.teachers[0].provideFeedback(
        DataClass.students[1],
        DataClass.courses[2],
        "Sinh viên tiếp thu bài tốt"
    )
    DataClass.teachers[0].provideFeedback(
        DataClass.students[1],
        DataClass.courses[3],
        "Sinh viên tiếp thu bài tốt"
    )

}

suspend fun createCourses(coursesManager: CoursesManager) {
    // Tạo môn học
    coursesManager.insert(DataClass.courses)
}

suspend fun hireTeacher(teachersManager: TeachersManager) {
    teachersManager.insert(DataClass.teachers)
}

suspend fun registerStudent(studentsManager: StudentsManager) {
    studentsManager.insert(DataClass.students)
}

fun addClassrooms(classroomsManager: SchoolManager) {
    DataClass.classrooms.forEach { classroomsManager.addClassroom(it) }
}

fun createSchedules(schedulesManager: SchoolManager) {
    // Tạo lịch học mẫu
    schedulesManager.addSchedules(DataClass.sampleSchedule)
}














