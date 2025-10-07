package com.example.summaytask12.system

import com.example.summaytask12.generics.addItem
import com.example.summaytask12.extensions.calculateAnnualSalary
import com.example.summaytask12.extensions.capitalizeFirst
import com.example.summaytask12.generics.printInfo
import com.example.summaytask12.interfaces.BaseInterface
import com.example.summaytask12.model.Teacher
import com.example.summaytask12.data.DataClass
import com.example.summaytask12.data.DataClass.courses
import com.example.summaytask12.data.DataClass.students
import com.example.summaytask12.generics.deleteItem
import com.example.summaytask12.generics.updateItem

class TeachersManager : BaseInterface<Teacher> {
    private val teachers = mutableListOf<Teacher>()

//    fun hireTeacher(teacher: List<Teacher>) {
//        addItem(teachers, teacher) { a, b -> a.id == b.id }
//        for (tea in teacher) {
//            if (teachers.any { it.id == tea.id }) {
//                println("Sinh viên với ID ${tea.id} đã tồn tại!")
//            } else {
//                teachers.addAll(listOf(tea))
//                println("Đã đăng ký sinh viên: ${tea.name}")
//            }
//        }
//    }

//    fun findTeacherByName(name: String): Teacher? {
//        return teachers.find { it.name == name }
//    }

//    fun displayAllTeachers() {
////        println("Danh sách tất cả thầy cô")
////        var i = 0
////        while (i < teachers.size) {
////            println("- ${teachers[i].name.capitalizeFirst()} - ${teachers[i].subject} - ${teachers[i].salary}")
////            i++
////        }
//        printInfo(teachers) { teacher ->
//            "${teacher.name.capitalizeFirst()} - ${teacher.subject} - ${teacher.salary}"
//        }
//    }


    fun getIdTeacher(id: Int) : Int? {
       val foundTeacher=teachers.find { it.id==id }
        return foundTeacher?.id
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

    // apply: Dùng để cấu hình đối tượng (thường dùng khi khởi tạo)
    suspend fun updateTeacher(id: Int, salaryTeacher: Double, subjectTeacher: String) {
        //  val teacher = teachers.find { it.id == id }
        val teacherId = getById(id)
        if (teacherId == null) {
            println("Vui lòng nhập lại thông tin")
        } else {
            val user = teacherId.also {
                println("Giáo viên trước khi apply: $it")
            }.apply {
                salary = salaryTeacher
                subject = subjectTeacher
            }
            println("Giáo viên sau khi apply: $user")
        }

    }

    override suspend fun getAll() {
        if (teachers.isEmpty()) {
            println("Chưa có giáo viên nào được thêm.")
            return
        }
        printInfo(teachers) { teacher ->
            "${teacher.id} - ${teacher.name.capitalizeFirst()} - ${teacher.subject} - ${teacher.salary}"
        }
    }

    override suspend fun getById(id: Int): Teacher? {
        return teachers.find { it.id == id }
    }

    override suspend fun getByName(name: String): List<Teacher> {
        return teachers.filter { it.name.equals(name, ignoreCase = true) }
    }

    override suspend fun insert(item: List<Teacher>) {
        addItem(teachers, item) { a, b -> a.id == b.id }
        println("Đã thêm ${item.size} giáo viên mới.")
    }

    override suspend fun update(id: Int, item: Teacher) {
//        val index = teachers.indexOfFirst { it.id == id }
//        if (index != -1) {
//            teachers[index] = item
//            println("Đã cập nhật thông tin giáo viên có ID: $id")
//        } else {
//            println("Không tìm thấy giáo viên có ID: $id để cập nhật.")
//        }
        updateItem(teachers, id, item) { it.id }
    }

    override suspend fun delete(id: Int) {
//        val removed = teachers.removeIf { it.id == id }
//        if (removed) {
//            println("Đã xóa giáo viên có ID: $id")
//        } else {
//            println("Không tìm thấy giáo viên có ID: $id để xóa.")
//        }
        deleteItem(teachers, id) { it.id }
    }
}
