package com.example.summaytask12.system
import com.example.summaytask12.extensions.calculateAnnualSalary
import com.example.summaytask12.extensions.capitalizeFirst
import com.example.summaytask12.model.Teacher
import com.example.summaytask12.system.DataClass.courses
import com.example.summaytask12.system.DataClass.students

class TeachersManager {
    private val teachers = mutableListOf<Teacher>()

    fun hireTeacher(teacher: List<Teacher>) {
//        addItem<Teacher>(teachers,teacher)
        for (tea in teacher) {
            if (teachers.any { it.id == tea.id }) {
                println("Sinh viên với ID ${tea.id} đã tồn tại!")
            } else {
                teachers.addAll(listOf(tea))
                println("Đã đăng ký sinh viên: ${tea.name}")
            }
        }
    }

    fun displayAllTeachers() {
        println("Danh sách tất cả thầy cô")
        var i = 0
        while (i < teachers.size) {
            println("- ${teachers[i].name.capitalizeFirst()} - ${teachers[i].subject} - ${teachers[i].salary}")
            i++
        }
    }

    // 5. NULL SAFETY
    fun getTeacherSalary(teacherId: Int): String {
        val teacher = teachers.firstOrNull { it.id == teacherId }

        // Sử dụng safe call và elvis operator
        return teacher?.salary?.let {
            "Lương của ${teacher.name}: $it"
        } ?: "Không tìm thấy thông tin lương cho giáo viên ID: $teacherId"
    }

    fun getgroupsOfTeachersBySubject() {
        // Sử dụng groupBy để nhóm giáo viên theo bộ môn
        teachers
            .groupBy { it.subject }
            .forEach { (subject, teachers) -> println("$subject: $teachers") }
    }

    fun calculateAnnualSalaryTeacher(teachers: List<Teacher>) {
        for (i in teachers.indices) {
            println(
                "Tổng số tiền lương của giáo viên ${teachers[i].name} với mã giáo viên ${teachers[i].id} là: " +
                        "${teachers[i].calculateAnnualSalary()}"
            )
        }
    }

    fun performUniversityAudit(): String {
        return run {
            val studentCount = students.size
            val teacherCount = DataClass.teachers.size
            val courseCount = courses.size
            """
            |Tổng sinh viên: $studentCount
            |Tổng giáo viên: $teacherCount
            |Tổng môn học: $courseCount
            """.trimMargin()
        }
    }
}