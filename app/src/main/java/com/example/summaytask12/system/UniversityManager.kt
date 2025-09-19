package com.example.summaytask12.system

import com.example.summaytask12.enum.AgeRange
import com.example.summaytask12.enum.StatusSchedule
import com.example.summaytask12.extensions.calculateAnnualSalary
import com.example.summaytask12.extensions.calculateTuition
import com.example.summaytask12.extensions.capitalizeFirst
import com.example.summaytask12.extensions.filterEligibleForGraduation
import com.example.summaytask12.extensions.getGradeLevel
import com.example.summaytask12.extensions.getTotalCredits
import com.example.summaytask12.extensions.sortByGPADescending
import com.example.summaytask12.model.Classroom
import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Schedule
import com.example.summaytask12.model.Student
import com.example.summaytask12.model.Teacher
import kotlinx.coroutines.delay
import kotlin.random.Random

class UniversityManager {

    private val courses = mutableListOf<Course>()
    private val students = mutableListOf<Student>()
    private val teachers = mutableListOf<Teacher>()
    private val classrooms = mutableListOf<Classroom>()
    private val schedules = mutableListOf<Schedule>()


    fun addClassroom(classroom: Classroom) {
        if (classrooms.none { it.roomId == classroom.roomId }) {
            classrooms.add(classroom)
            println("Đã thêm phòng học: ${classroom.roomNumber}")
        } else {
            println("Phòng học với ID ${classroom.roomId} đã tồn tại!")
        }
    }

    fun addSchedules(schedule: List<Schedule>) {
        schedules.addAll(schedule)
    }

    fun createSchedule(scheduleRequest: Schedule) {
        val existingClassroom = classrooms.find { it.roomId == scheduleRequest.classroom.roomId }
        if (existingClassroom == null) {
            println("${scheduleRequest.classroom.roomNumber} không tồn tại trong hệ thống.")
            return
        }

        if (scheduleRequest.teacher.subject != scheduleRequest.course.courseName) {
            println("Giáo viên ${scheduleRequest.teacher.name} không được phân công dạy môn ${scheduleRequest.course.courseName}.")
            return
        }
        if (scheduleRequest.classroom.status==StatusSchedule.SCHEDULED){
            println("Phòng đã có người đăng kí")
            return
        }

        val conflictingSchedule = schedules.firstOrNull {
            it.classroom.roomId == existingClassroom.roomId
                    it.classroom.status == StatusSchedule.SCHEDULED
        }

        if (conflictingSchedule != null) {
            println(
                "Phòng ${existingClassroom.roomNumber} đã được xếp lịch cho môn " +
                        "${conflictingSchedule.course.courseName} (GV: ${conflictingSchedule.teacher.name}) " +
                        "vào Thứ ${scheduleRequest.dayOfWeek + 1}, lúc ${scheduleRequest.startTime}."
            )
            return
        }



            existingClassroom.status = StatusSchedule.SCHEDULED


        val newScheduleEntry = scheduleRequest.copy(
            classroom = existingClassroom
        )

        schedules.add(newScheduleEntry)
        println(
            "Đã tạo lịch học mới thành công: " +
                    "${newScheduleEntry.teacher.name} - ${newScheduleEntry.course.courseName} " +
                    "- Phòng ${newScheduleEntry.classroom.roomNumber}" +
                    " (Trạng thái phòng: ${newScheduleEntry.classroom.status})"
        )
    }

    private fun findScheduleById(id: Int): Schedule? {
        return schedules.find { it.id == id }
    }

    fun cancelSchedule(scheduleId: Int) {
        val schedule = findScheduleById(scheduleId)
        if (schedule != null) {
            schedules.remove(schedule)
            schedule.classroom.status = StatusSchedule.CANCELED
            println("Đã hủy lịch học cho môn ${schedule.course.courseName}")
        } else {
            println("Không tìm thấy sinh viên với ID: $scheduleId")
        }
    }

    // Lấy danh sách lịch học
    fun getSchedules(): List<Schedule> {
        return schedules
    }

    // Lấy danh sách phòng học
    fun getClassrooms(): List<Classroom> {
        return classrooms
    }

    // Higher-order function
    fun getClassroomsIsEmpty(condition: (Classroom) -> Boolean, action: (Classroom) -> Unit) {
        println(classrooms.filter(condition).forEach(action))
    }
//    fun getClassroomsIsEmpty() {
//        println(classrooms.filter {(it.status == StatusSchedule.DRUM || it.status == StatusSchedule.CANCELED) })
//    }

    fun addCourses(course: List<Course>) {
        courses.addAll(course)
    }

    fun registerStudent(student: List<Student>) {
        for (i in student.indices) {
            if (students.any { it.id == student[i].id }) {
                println("Sinh viên với ID ${student[i].id} đã tồn tại!")
            } else {
                students.add(student[i])
                println("Đã đăng ký sinh viên: ${student[i].name}")
            }
        }
    }

    fun hireTeacher(teacher: List<Teacher>) {
        for (tea in teacher) {
            if (teachers.any { it.id == tea.id }) {
                println("Sinh viên với ID ${tea.id} đã tồn tại!")
            } else {
                teachers.addAll(listOf(tea))
                println("Đã đăng ký sinh viên: ${tea.name}")
            }
        }
    }

    private fun findStudentById(id: Int): Student? {
        return students.find { it.id == id }
    }

    fun findCourseById(id: String): Course? {
        return courses.find { it.courseId == id }
    }

    fun findStudentsByName(name: String): List<Student> {
        // Sử dụng filter với contains (ignore case)
        return students.filter { it.name.contains(name, ignoreCase = true) }
    }

    fun addStudent(student: Student) {
        val studentId = findStudentById(student.id)
        if (studentId == null) {
            this.students.add(student)
            println("Đã đăng ký sinh viên: ${student.name}")
        } else {
            println("Mã sinh viên đã có")
        }
    }

    fun cleanStudent(studentId: Int) {
        val student = findStudentById(studentId)
        if (student != null) {
            students.remove(student)
            println("Sinh viên ${student.name} đã được xóa khỏi danh sách.")
        } else {
            println("Không tìm thấy sinh viên với ID: $studentId")
        }
    }

    fun updateStudent(studentId: Int, newStudent: Student) {
        val index = students.indexOfFirst { it.id == studentId }
        if (index != -1) {
            students[index] = newStudent
            println("Sinh viên có ID $studentId đã được cập nhật.")
        } else {
            println("Không tìm thấy sinh viên với ID: $studentId")
        }
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

    fun displayAllStudents() {
        println("Danh sách sinh viên:")
        // For loop với index
        for ((index, student) in students.withIndex()) {
            println("${index + 1}. ${student.name} (ID: ${student.id}, GPA: ${student.gpa})")

        }
        println("Tổng số sinh viên: ${students.size} sinh viên")
    }

    fun calculateTuitionStudent(students: List<Student>, creditPrice: Double) {
        for (i in 0..<students.size) {
            println(
                "Tổng số tiền học phí của sinh viên ${students[i].name} với mã sinh viên ${students[i].id} là: ${
                    students[i].calculateTuition(creditPrice)
                }"
            )
        }
    }

    fun calculateAnnualSalaryTeacher(teachers: List<Teacher>) {
        for (i in 0 until teachers.size) {
            println(
                "Tổng số tiền lương của giáo viên ${teachers[i].name} với mã giáo viên ${teachers[i].id} là: " +
                        "${teachers[i].calculateAnnualSalary()}"
            )
        }
    }

    fun displayAllCourses() {
        println("Danh sách môn học")
        // For each loop
        courses.forEach { course ->
            println("- ${course.courseName} (${course.courseId}, ${course.credit} tín chỉ)")
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

    //Couninue
    fun printStudentExcellent(students: List<Student>) {
        println("In danh sách sinh viên xếp loại giỏi và xuất sắc")
        for (i in students.indices) {
            if (students[i].gpa >= 3.2) {
                continue
            }
            println(students[i].name)
        }
    }


    fun getTopPerformingStudents(limit: Int = 5) {
        println("In danh sách sinh viên xếp loại giỏi và xuất sắc")
//        students
//            .sortByGPADescending()
//            .take(limit)
//            .forEach { println(it) }
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

    fun getTotalCreditsStudent(students: List<Student>) {
        for (i in students.size - 1 downTo 0) {
            println("Tổng số tín chỉ sinh viên ${students[i].name} là: ${students[i].getTotalCredits()}")
        }
    }

    fun getCourseStatistics(): Map<String, Any> {
        return mapOf(
            "total_courses" to courses.size,
            "total_credits" to courses.sumOf { it.credit },
            "advanced_courses" to courses.count { it.credit >= 3 },
            "average_credits" to if (courses.isNotEmpty()) courses.sumOf { it.credit } / courses.size else 0
        )
    }

    fun getStudentCountByAgeRange(student: Student): Map<String, Int> {
        val youngStudents = students.count { it.age < 18 }
        val adultStudents = students.count { it.age in 18..20 }
        val matureStudents = students.count { it.age > 20 }

        return mapOf(
            "Dưới 18" to youngStudents,
            "18-20" to adultStudents,
            "Trên 20" to matureStudents
        )

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
    fun generateStudentReport(studentId: Int): String {
        val student = findStudentById(studentId)
        return student?.let {
            """
            |Tên: ${it.name}
            |ID: ${it.id}
            |GPA: ${it.gpa}
            |Số môn đã đăng ký: ${it.getEnrolledCourses().size}           
            """.trimMargin()
        } ?: "Không tìm thấy sinh viên với ID: $studentId"
    }

    // apply: Dùng để cấu hình đối tượng (thường dùng khi khởi tạo)
    fun updateTeacher(teacher: Teacher) {
        var user = teacher.apply {
            salary = 20000000.0
            subject = "Tin Học Đại Cương"
        }
        println("Giáo viên sau khi apply: $user")
    }

    // Hàm một dòng
    fun isUniversityEmpty(): Boolean = students.isEmpty() && teachers.isEmpty() && courses.isEmpty()


    // 5. NULL SAFETY
    fun getTeacherSalary(teacherId: Int): String {
        val teacher = teachers.firstOrNull { it.id == teacherId }

        // Sử dụng safe call và elvis operator
        return teacher?.salary?.let {
            "Lương của ${teacher.name}: $it"
        } ?: "Không tìm thấy thông tin lương cho giáo viên ID: $teacherId"
    }

    fun updateTeacherSalary(teacherId: Int, newSalary: Double?): Boolean {
        val teacher = teachers.firstOrNull { it.id == teacherId }

        // Sử dụng safe call
        teacher?.salary = newSalary
        return teacher != null
    }


    fun getgroupsOfTeachersBySubject() {
        // Sử dụng groupBy để nhóm giáo viên theo bộ môn
        teachers
            .groupBy { it.subject }
            .forEach { (subject, teachers) -> println("$subject: $teachers") }
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

    // Hàm với default parameter
    fun getCalculateAverageGPA(roundToDecimal: Int = 2): Double {
        if (students.isEmpty()) return 0.0

        val totalGPA = students.sumOf { it.gpa }
        val average = totalGPA / students.size

        // Ép kiểu và làm tròn
        return String.format("%.${roundToDecimal}f", average).toDouble()
    }

    fun performUniversityAudit(): String {
        return run {
            val studentCount = students.size
            val teacherCount = teachers.size
            val courseCount = courses.size
            """
            |Tổng sinh viên: $studentCount
            |Tổng giáo viên: $teacherCount
            |Tổng môn học: $courseCount
            """.trimMargin()
        }
    }

    //If -else
    fun duplicateTest(students: List<Student>) {
        for (i in 0 until students.size) {
            for (j in i + 1 until students.size) {
                if (students[i].id.equals(students[j].id)) {
                    println("Sinh viên ${students[i].name} trùng id với sinh viên ${students[j].name}")
                }
            }
        }
    }

    //When
    fun studentClassification(students: List<Student>) {
        println("Danh sách sinh viên theo xếp loại")
        for (student in students) {
            when (student.gpa) {
                in 0.0..1.5 -> println("Sinh viên ${student.name} xếp loại yếu")
                in 1.6..2.0 -> println("Sinh viên ${student.name} xếp loại dưới trung bình")
                in 2.1..3.0 -> println("Sinh viên ${student.name} xếp loại trung bình")
                in 3.1..3.5 -> println("Sinh viên ${student.name} xếp loại khá")
                in 3.6..4.0 -> println("Sinh viên ${student.name} xếp loại giỏi")
                else -> println("Điểm GPA không hợp lệ")
            }
        }
    }

    fun conditionClassification(students: List<Student>) {
        println("Phân loại sinh viên theo điểm GPA:")
        for (student in students) {
            val gradeLevel = student.getGradeLevel()
            // In ra tên sinh viên và nhãn của xếp loại
            println("Sinh viên ${student.name} (${student.gpa}): ${gradeLevel.label}")
        }
    }

    suspend fun processGraduationAsync(students: List<Student>) {
        println("Đang xử lý tốt nghiệp...")
        // Giả lập delay
        delay(3000)
        println(
            students.filterEligibleForGraduation(MIN_GPA_FOR_GRADUATION).forEach { println(it) })
    }


    companion object {
        const val MIN_GPA_FOR_GRADUATION = 2.0
    }
}
