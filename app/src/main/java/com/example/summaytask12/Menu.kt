package com.example.summaytask12

import com.example.summaytask12.enum.AgeRange
import com.example.summaytask12.enum.StatusSchedule
import com.example.summaytask12.model.Classroom
import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Student
import com.example.summaytask12.model.Teacher
import com.example.summaytask12.system.DataClass.classrooms
import com.example.summaytask12.system.DataClass.courses
import com.example.summaytask12.system.DataClass.sampleSchedule
import com.example.summaytask12.system.DataClass.sampleSchedule2
import com.example.summaytask12.system.DataClass.students
import com.example.summaytask12.system.DataClass.teachers
import com.example.summaytask12.system.UniversityManager
import kotlinx.coroutines.runBlocking

fun main() {
    val universityManager = UniversityManager()
    createData(universityManager)

    var select: Int
    var addressString: String = "Hòa Bình"
    var numberId: Int = 1
    var creditPrice: Double = 3000000.0
    var teacherId: Int = 102
    var studentId: Int = 1
    var student: Student
    var updateStudentId: Int = 1
    var id: Int = 1
    var name: String = "Nguyễn Văn A"
    var age: Int = 20
    var gpa: Double = 3.5
    var address: String = "Hòa Bình"
    var idSchedule: Int = 0
    do {
        getList()
        select = readln().toIntOrNull() ?: 0
        if (select == 24) {
            println("Nhập địa chỉ cần tìm kiếm :")
            addressString = readln().toString()
        }
        if (select == 5) {
            println("Nhập id cần tìm kiếm :")
            numberId = readln().toInt()
        }
        if (select == 6) {
            println("Nhập số tiền học phí :")
            creditPrice = readln().toDouble()
        }
        if (select == 17) {
            println("Nhập id cần tìm kiếm :")
            teacherId = readln().toInt()
        }
        if (select == 9) {
            println("Nhập id cần xóa :")
            studentId = readln().toInt()
        }
        if (select == 1) {
            println("Nhập thông tin cần thêm :")
            id = readln().toInt()
            name = readln().toString()
            age = readln().toInt()
            gpa = readln().toDouble()
            address = readln().toString()
        }
        if (select == 25) {
            println("Nhập Id cần cập nhật :")
            updateStudentId = readln().toInt()
            println("Nhập thông tin cần cập nhật :")
            id = readln().toInt()
            name = readln().toString()
            age = readln().toInt()
            gpa = readln().toDouble()
            address = readln().toString()
        }
        if (select == 26) {
            println("Nhập tên cần tìm kiếm :")
            name = readln().toString()
            universityManager.findStudentsByName(name)
        }
        if (select == 29) {
            println("Nhập địa chỉ đăng kí phonòng học cần xóa")
            idSchedule = readln().toInt()
        }
        when (select) {
            1 -> universityManager.addStudent(Student(id, name, age, gpa, address))
            2 -> universityManager.displayAllStudents()
            3 -> universityManager.displayAllTeachers()
            4 -> universityManager.displayAllCourses()
            5 -> generateStudentReport(numberId, universityManager)
            6 -> universityManager.calculateTuitionStudent(students, creditPrice)
            7 -> universityManager.calculateAnnualSalaryTeacher(teachers)
            8 -> universityManager.printStudentExcellent(students)
            9 -> universityManager.cleanStudent(studentId)
            10 -> universityManager.getTopPerformingStudents()
            11 -> getStudentsEligibleForGraduation(students, universityManager)
            12 -> universityManager.processGraduation()
            13 -> universityManager.getTotalCreditsStudent(students)
            14 -> getCourseStatistics(universityManager)
            15 -> getStudentCountByAgeRange(universityManager)
            16 -> getCalculateAverageGPA(universityManager)
            17 -> println(universityManager.getTeacherSalary(teacherId))
            18 -> universityManager.createSampleStudent()
            19 -> println(universityManager.performUniversityAudit())
            20 -> universityManager.duplicateTest(students)
            21 -> universityManager.conditionClassification(students)
            22 -> println(universityManager.getgroupsOfTeachersBySubject())
            23 -> students.forEach { it.listCourses() }
            24 -> universityManager.addressCheck(students, addressString)
            25 -> universityManager.updateStudent(
                updateStudentId,
                Student(id, name, age, gpa, address)
            )
            26 -> println(universityManager.findStudentsByName(name))
            27 -> printListClassroom(universityManager)
            28 -> printListSchedules(universityManager)
            29 -> universityManager.cancelSchedule(idSchedule)
            30 ->universityManager.createSchedule(sampleSchedule2)
            31 -> getClassroomsIsEmpty(universityManager)
            else -> println("Lựa chọn không hợp lệ")
        }
    } while (select != 0)
}

fun enterValue() {
    println("Nhập Id cần cập nhật :")
    val updateStudentId = readln().toInt()
    println("Nhập thông tin cần cập nhật :")
    val id = readln().toInt()
    val name = readln().toString()
    val age = readln().toInt()
    val gpa = readln().toDouble()
    val address = readln().toString()
}

fun createData(universityManager: UniversityManager) {
    //tạo môn học
    universityManager.addCourses(courses)
    //Thuê giáo viên
    universityManager.hireTeacher(teachers)
    //Thêm sinh viên
    universityManager.registerStudent(students)

    // Sinh viên đăng ký môn học
    students[0].enrollStudent(courses[0])
    students[0].enrollStudent(courses[1])
    students[1].enrollStudent(courses[2])
    students[1].enrollStudent(courses[3])

    // Giáo viên được phân công môn học
    teachers[0].assignCourse(courses[0])
    teachers[1].assignCourse(courses[1])
    teachers[2].assignCourse(courses[3])

    // Giáo viên chấm điểm
    teachers[0].grade(students[0], courses[0], 8.5)
    teachers[1].grade(students[0], courses[1], 7.8)
    teachers[2].grade(students[1], courses[2], 6.5)
    teachers[1].grade(students[1], courses[3], 5.5)
    println("\n")

    classrooms.forEach { universityManager.addClassroom(it) }

    // Tạo lịch học mẫu
    universityManager.addSchedules(sampleSchedule)
}

fun getList() {
    println("1.Thêm sinh viên")
    println("2.In danh sách sinh viên")
    println("3.In danh sách môn học")
    println("4.In danh sách giáo viên")
    println("5.Tạo báo cáo sinh viên cần tìm kiếm")
    println("6.Tổng học phí sinh viên")
    println("7.Tiền lương từng giáo viên")
    println("8.Tìm kiếm sinh viên theo tên")
    println("9.Xóa sinh viên")
    println("10.Năm sinh viên có thành tích xuất sắc nhất")
    println("11.Sinh viên đủ điều kiện tốt nghiệp")
    println("12.Thống kê sinh viên đỗ và trượt")
    println("13.Tổng số tín chỉ sinh viên đã đăng kí")
    println("14.Lọc thông tin sinh viên theo yêu cầu")
    println("15.Thông kê sinh viên theo độ tuổi")
    println("17.Lương giáo viên theo Id")
    println("16.Điểm GPA trùng bình của sinh viên")
    println("18.Thêm sinh viên")
    println("19.Thôngs kê")
    println("20.Kiểm tra xem có trùng Id")
    println("21.Danh sách xếp loại")
    println("22.Moon học giáo viện dạy")
    println("23.Sinh viên đăng kí môn học")
    println("24.Tim kiếm sinh viên theo địa chir")
    println("25.Cập nhật sinh viên")
    println("26.Tìm kiếm sinh viên theo tên")
    println("27. Quản lý Phòng học")
    println("28. Quản lý Lịch học")
    println("29. Hủy đăng kí phòng học")
    println("30. Đăng kí phòng học")
    println("31. Kiểm tra lớp còn trống")
    println("0.Thoát")
    print("Nhập lựa chọn :")
}

fun getCourseStatistics(universityManager: UniversityManager) {
    println("In ra thống kê môn học :${universityManager.getCourseStatistics().entries}")
    println("Tổng số môn học: ${universityManager.getCourseStatistics()["total_courses"]}")
    println("Tổng số tín chỉ: ${universityManager.getCourseStatistics().values}")
    println(
        "Số môn học lớn hơn 3 tín: ${
            universityManager.getCourseStatistics()
                .getOrDefault("advanced_courses", "Không có môn nào")
        }"
    )
    println("Trung bình số tín chỉ: ${universityManager.getCourseStatistics()["average_credits"]}")
}

fun getStudentCountByAgeRange(universityManager: UniversityManager) {
    println("Số lượng sinh viên nhỏ hơn 18 tuổi: ${universityManager.getStudentCountByAgeRange()[AgeRange.YOUNG]}")
    println("Số lượng sinh viên từ 18 đến 20 tuổi: ${universityManager.getStudentCountByAgeRange()[AgeRange.ADULT]}")
    println("Số lượng sinh viên trên 20 tuổi: ${universityManager.getStudentCountByAgeRange()[AgeRange.MATURE]}")
}

fun getCalculateAverageGPA(universityManager: UniversityManager) {
    println("Điểm GPA trung bình của tất cả sinh viên: ${universityManager.getCalculateAverageGPA()}")
}

fun getgroupsOfTeachersBySubject(universityManager: UniversityManager) {
    println("Nhóm các thầy cô theo từng bộ môn: ${universityManager.getgroupsOfTeachersBySubject()}")
}

fun updateSalaryTeacher(universityManager: UniversityManager) {
    universityManager.updateTeacherSalary(102, 25000000.0)
}

fun generateStudentReport(studentId: Int, universityManager: UniversityManager) {
    println("Thông tin cần tìm : ${universityManager.generateStudentReport(studentId)}")
}

fun getTopPerformingStudents(universityManager: UniversityManager) {
    println(universityManager.getTopPerformingStudents().toString())
}

fun getStudentsEligibleForGraduation(
    students: List<Student>,
    universityManager: UniversityManager
) {
    runBlocking {
        val graduatedStudents = universityManager.processGraduationAsync(students).toString()
        println(graduatedStudents)
    }
}

fun printListClassroom(universityManager: UniversityManager) {
    println("Quản lí phòng học")
    println("Danh sách phòng học:")
    var getClassrooms = universityManager.getClassrooms()
    getClassrooms.forEachIndexed { index, room ->
        println("${index + 1}. Phòng ${room.roomNumber} (Sức chứa: ${room.capacity}) - Tiện nghi: ${room.facilities.joinToString()} -Trạng thái :${room.status}")
    }
    println("Tổng số phòng: ${classrooms.size}")
}


fun printListSchedules(universityManager: UniversityManager) {
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

fun getClassroomsIsEmpty(universityManager: UniversityManager){
    universityManager.getClassroomsIsEmpty(
        condition = { cl -> cl.status == StatusSchedule.DRUM },
        action = { cl -> println("Phòng TRỐNG: ${cl.roomNumber}, Sức chứa: ${cl.capacity}, Tiện nghi: ${cl.facilities.joinToString()}, Trạng thái: ${cl.status}") }
    )
    universityManager.getClassroomsIsEmpty(
        condition = { cl -> cl.status == StatusSchedule.CANCELED },
        action = { cl -> println("Phòng ĐÃ HỦY LỊCH: ${cl.roomNumber}, Sức chứa: ${cl.capacity}, Tiện nghi: ${cl.facilities.joinToString()}, Trạng thái: ${cl.status}") }
    )
}













