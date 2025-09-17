package com.example.summaytask12

import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Enrollment
import com.example.summaytask12.model.Student
import com.example.summaytask12.model.Teacher
import com.example.summaytask12.utils.demoLet

fun main() {
    val courses = listOf(
        Course("CS101", "Lập trình Kotlin", 3),
        Course("CS102", "Cấu trúc dữ liệu", 4),
        Course("CS103", "Cơ sở dữ liệu", 3),
        Course("CS104", "Mạng máy tính", 3),
        Course("CS105", "Trí tuệ nhân tạo", 4)
    )

    // Hiển thị thông tin môn học
    courses[0].displayCourse()
    courses[1].displayCourse()
    courses[2].displayCourse()

    val students = listOf(
        Student(1, "Nguyễn Văn Anh", 20, 3.2, mutableListOf()),
        Student(2, "Trần Thị Bình", 21, 3.6, mutableListOf()),
        Student(3, "Lê Văn Chí", 19, 2.8, mutableListOf()),
        Student(5, "Nguyễn Văn Hà", 18, 1.6,  mutableListOf()),
        Student(6, "Nguyễn Văn Hùng", 20, 4.0,  mutableListOf()),
        Student(7, "Nguyễn Văn Lan", 17, 2.5,  mutableListOf()),
        Student(8, "Nguyễn Văn Minh", 19, 2.4,  mutableListOf()),
        Student(9, "Nguyễn Văn Nga", 20, 2.8,  mutableListOf()),
        Student(10, "Nguyễn Văn Tú", 19, 3.5,  mutableListOf())
    )

    students[0].enrollStudent(courses[0])
    students[0].enrollStudent(courses[1])
    students[0].listCourses()

    val teachers = listOf(
        Teacher(101, "Phạm Thị D", 40, "Cấu trúc dữ liệu",18000000.0),
        Teacher(102, "Ngô Văn E", 38, "Cơ sở dữ liệu"),
        Teacher(103, "Đặng Văn F", 45, "Trí tuệ nhân tạo")
    )
    teachers[0].grade(students[0], courses[0], 8.5)
    val enrollments = listOf(
        Enrollment(students[0], courses[0], 8.5),
        Enrollment(students[0], courses[1], 8.0),
        Enrollment(students[1], courses[2], 9.2),
        Enrollment(students[1], courses[4], 7.5),
        Enrollment(students[2], courses[0], 6.8),
        Enrollment(students[2], courses[3], null) // chưa có điểm
    )
    enrollments[0].displayEnrollment()
    enrollments[1].displayEnrollment()

    // Dùng phương thức showInfo từ UniversityMember
    students[0].showInfo()
    teachers[0].showInfo()

    // Gọi các hàm từ Person
    students[0].showInfoName()
    teachers[1].showInfoName()
    teachers[2].showSubject("Java nâng cao")
}



