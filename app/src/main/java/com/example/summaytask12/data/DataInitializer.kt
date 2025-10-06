package com.example.summaytask12.data

import com.example.summaytask12.system.CoursesManager
import com.example.summaytask12.system.SchoolManager
import com.example.summaytask12.system.StudentsManager
import com.example.summaytask12.system.TeachersManager


class DataInitializer(
    private val coursesManager: CoursesManager,
    private val schoolManager: SchoolManager,
    private val studentsManager: StudentsManager,
    private val teachersManager: TeachersManager
) {

    suspend fun initializeAllData() {
        // Khởi tạo dữ liệu đồng bộ
        createCourses()
        hireTeachers()
        registerStudents()
        addClassrooms()
        createSchedules()
        createSampleData()
    }

    private suspend fun createCourses() {
        coursesManager.insert(DataClass.courses)
    }

    private suspend fun hireTeachers() {
        teachersManager.insert(DataClass.teachers)
    }

    private suspend fun registerStudents() {
        studentsManager.insert(DataClass.students)
    }

    private suspend fun addClassrooms() {
        schoolManager.insertClassrooms(DataClass.classrooms)
    }

    private suspend fun createSchedules() {
        schoolManager.insertSchedules(DataClass.sampleSchedule)
    }

    private fun createSampleData() {
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
}