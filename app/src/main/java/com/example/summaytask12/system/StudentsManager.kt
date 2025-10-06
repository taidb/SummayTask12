package com.example.summaytask12.system

import com.example.summaytask12.generics.addItem
import com.example.summaytask12.enum.AgeRange
import com.example.summaytask12.extensions.calculateTuition
import com.example.summaytask12.extensions.dultStudents
import com.example.summaytask12.extensions.filterEligibleForGraduation
import com.example.summaytask12.extensions.getGradeLevel
import com.example.summaytask12.extensions.getTotalCredits
import com.example.summaytask12.extensions.sortByGPADescending
import com.example.summaytask12.generics.printInfo
import com.example.summaytask12.interfaces.BaseInterface
import com.example.summaytask12.model.Student
import com.example.summaytask12.sealed.StatusStudent
import com.example.summaytask12.system.SchoolManager.Companion.MIN_GPA_FOR_GRADUATION
import kotlinx.coroutines.delay
import kotlin.random.Random

class StudentsManager : BaseInterface<Student> {
    private val students = mutableListOf<Student>()

    //   fun registerStudent(student: List<Student>) {
    //     addItem(students, student) { a, b -> a.id == b.id }
//        for (i in student.indices) {
//            if (students.any { it.id == student[i].id }) {
//                println("Sinh viên với ID ${student[i].id} đã tồn tại!")
//            } else {
//                students.add(student[i])
//                println("Đã đăng ký sinh viên: ${student[i].name}")
//            }
//        }
    //   }

//    private fun findStudentById(id: Int): Student? {
//        return students.find { it.id == id }
//    }
//
//    fun findCourseById(id: String): Course? {
//        return courses.find { it.courseId == id }
//    }
    //Kotlin Reflection (::class)


//    fun findStudentsByName(name: String): List<Student> {
//        // Sử dụng filter với contains (ignore case)
//        return students.filter { it.name.contains(name, ignoreCase = true) }
//    }

    suspend fun addStudent(student: Student) {
        val studentId = getById(student.id)
        if (studentId == null) {
            this.students.add(student)
            println("Đã đăng ký sinh viên: ${student.name}")
        } else {
            println("Mã sinh viên đã có")
        }
    }

//    suspend fun cleanStudent(studentId: Int) {
//        val student = getById(studentId)
//        if (student != null) {
//            students.remove(student)
//            println("Sinh viên ${student.name} đã được xóa khỏi danh sách.")
//        } else {
//            println("Không tìm thấy sinh viên với ID: $studentId")
//        }
//    }

//    fun updateStudent(studentId: Int, newStudent: Student) {
//        val index = students.indexOfFirst { it.id == studentId }
//        if (index != -1) {
//            students[index] = newStudent
//            println("Sinh viên có ID $studentId đã được cập nhật.")
//        } else {
//            println("Không tìm thấy sinh viên với ID: $studentId")
//        }
//    }

    fun getTopPerformingStudents(limit: Int = 5) {
        println("In danh sách sinh viên xếp loại giỏi và xuất sắc")
        println(students.sortByGPADescending().take(limit).joinToString("\n"))
    }

//    fun getStudentsEligibleForGraduation(students: List<Student>) {
//        students.filterEligibleForGraduation(MIN_GPA_FOR_GRADUATION).forEach { println(it) }
//    }

    fun processGraduation() {
        // Sử dụng for loop với range
        for (i in 0 until students.size) {
            val student = students[i]
            val status = if (student.gpa >= MIN_GPA_FOR_GRADUATION) "Đậu" else "Trượt"
            println("${i + 1}. ${student.name} - GPA: ${student.gpa} - Kết quả: $status")
        }
    }

    fun getStudentCountByAgeRange(): Map<AgeRange, Int> {
        val young = students.count { it.age < 18 }
        val adult = students.count { it.age in 18..20 }
        val mature = students.count { it.age > 20 }

        return mapOf(
            AgeRange.YOUNG to young,
            AgeRange.ADULT to adult,
            AgeRange.MATURE to mature
        )
    }

    // 4.HÀM VÀ SCOPE FUNCTION
    // let: Dùng để biến đổi giá trị hoặc xử lý an toàn với null
    suspend fun generateStudentReport(studentId: Int): String {
        val student = getById(studentId)
        return student?.let {
            """
            |Tên: ${it.name}
            |ID: ${it.id}
            |GPA: ${it.gpa}
            |Số môn đã đăng ký: ${it.getEnrolledCourses().size}           
            """.trimMargin()
        } ?: "Không tìm thấy sinh viên với ID: $studentId"
    }

    fun getCalculateAverageGPA(roundToDecimal: Int = 2): Double {
        if (students.isEmpty()) return 0.0

        val totalGPA = students.sumOf { it.gpa }
        val average = totalGPA / students.size

        // Ép kiểu và làm tròn
        return String.format("%.${roundToDecimal}f", average).toDouble()
    }


    fun getTotalCreditsStudent(students: List<Student>) {
        for (i in students.size - 1 downTo 0) {
            println("Tổng số tín chỉ sinh viên ${students[i].name} là: ${students[i].getTotalCredits()}")
        }
    }

    // Named argument example
    fun createSampleStudent(
        id: Int = Random.nextInt(1000, 9999),
        name: String = "Tài",
        age: Int = 20,
        gpa: Double = Random.nextDouble(2.0, 4.0),
        address: String = "Nghệ An"
    ): Student {
        return Student(id, name, age, gpa, address)
    }

    fun conditionClassification(students: List<Student>) {
        println("Phân loại sinh viên theo điểm GPA:")
        for (student in students) {
            val gradeLevel = student.getGradeLevel()
            // In ra tên sinh viên và nhãn của xếp loại
            println("Sinh viên ${student.name} (${student.gpa}): ${gradeLevel.label}")
        }
    }


    //If -else
    fun duplicateTest(students: List<Student>) {
//        for (i in 0 until students.size) {
//            for (j in i + 1 until students.size) {
//                if (students[i].id.equals(students[j].id)) {
//                    println("Sinh viên ${students[i].name} trùng id với sinh viên ${students[j].name}")
//                }
//            }
//        }
        students.dultStudents()
    }

    fun calculateTuitionStudent(students: List<Student>, creditPrice: Double) {
        for (i in students.indices) {
            println(
                "Tổng số tiền học phí của sinh viên ${students[i].name} với mã sinh viên ${students[i].id} là: ${
                    students[i].calculateTuition(creditPrice)
                }"
            )
        }
    }

    suspend fun processGraduationAsync(students: List<Student>) {
        println("Đang xử lý tốt nghiệp...")
        // Giả lập delay
        delay(3000)
        println(
            students.filterEligibleForGraduation(MIN_GPA_FOR_GRADUATION).forEach { println(it) })
    }

    fun addressCheck(students: List<Student>, address: String) {
        for (student in students) {
            if (student.address.contains(address)) {
                println("Sinh viên ${student.name} có địa chỉ là $address")
            } else {
                println("Sinh viên không có địa chỉ là $address")
            }
        }
    }

    fun scholarshipStudents(students: List<Student>) {
        for (student in students) {
            if (student.gpa >= 3.5) {
                getScholarship(StatusStudent.Scholarship("Sinh viên ${student.name} được học bổng"))
            } else if (student.gpa < 1.0) {
                getScholarship(StatusStudent.Scholarship("Sinh viên ${student.name} không được học bổng"))
            } else {
                getScholarship(StatusStudent.Normal("Sinh viên ${student.name} không được học bổng"))
            }
        }
    }

    private fun getScholarship(status: StatusStudent){
        when(status){
            is StatusStudent.Scholarship -> println(status.message)
            is StatusStudent.Warning -> println(status.message)
            is StatusStudent.Normal -> println(status.message)

        }
    }

    //Higher-order function
    fun filterStudents(
        students: List<Student>,
        condition: (Student) -> Boolean
    ): List<Student> {
        return students.filter(condition)
    }

    override suspend fun getAll() {
        println("Danh sách sinh viên:")
        // For loop với index
//        for ((index, student) in students.withIndex()) {
//            println("${index + 1}. ${student.name} (ID: ${student.id}, GPA: ${student.gpa})")
//
//        }
//        println("Tổng số sinh viên: ${students.size} sinh viên")
        printInfo(students) { student ->
            "${student.name} (ID: ${student.id}, GPA: ${student.gpa})"
        }
    }

    override suspend fun getById(id: Int): Student? {
        return students.find { it.id == id }
    }

    override suspend fun getByName(name: String): List<Student> {
        return students.filter { it.name.contains(name, ignoreCase = true) }
    }

    override suspend fun delete(id: Int) {
        val student = getById(id)
        if (student != null) {
            students.remove(student)
            println("Sinh viên ${student.name} đã được xóa khỏi danh sách.")
        } else {
            println("Không tìm thấy sinh viên với ID: $id")
        }
    }

    override suspend fun update(id: Int, item: Student) {
        val index = students.indexOfFirst { it.id == id }
        if (index != -1) {
            students[index] = item
            println("Sinh viên có ID $id đã được cập nhật.")
        } else {
            println("Không tìm thấy sinh viên với ID: $id")
        }

    }

    override suspend fun insert(item: List<Student>) {
        addItem(students, item) { a, b -> a.id == b.id }
    }
}
