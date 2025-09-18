package com.example.summaytask12.system

import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Enrollment
import com.example.summaytask12.model.Student
import com.example.summaytask12.model.Teacher

object DataClass {
    val courses = listOf(
        Course("CS101", "Lập trình Kotlin", 3),
        Course("CS102", "Cấu trúc dữ liệu", 4),
        Course("CS103", "Cơ sở dữ liệu", 3),
        Course("CS104", "Mạng máy tính", 3),
        Course("CS105", "Trí tuệ nhân tạo", 4)
    )

    //Tạo sinh viên
    val students = listOf(
        Student(1, "Nguyễn Văn Anh", 20, 3.2, "Nghệ An"),
        Student(2, "Trần Thị Bình", 21, 3.6, "Hòa Bình"),
        Student(2, "Trần Thị Bình", 21, 3.6, "Hòa Bình"),
        Student(3, "Lê Văn Chí", 19, 2.8),
        Student(1, "Nguyễn Văn Tiến", 20, 3.2, "Ninh Bình"),
        Student(5, "Nguyễn Văn Hà", 18, 1.6,"Nghệ An"),
        Student(6, "Nguyễn Văn Hùng", 20, 4.0),
        Student(7, "Nguyễn Văn Lan", 17, 2.5),
        Student(8, "Nguyễn Văn Minh", 19, 2.4),
        Student(9, "Nguyễn Văn Nga", 21, 2.8),
        Student(10, "Nguyễn Văn Tú", 19, 3.5)
    )

    //Tạo giáo viên
    val teachers = listOf(
        Teacher(101, "phạm thị d", 40, "Cấu trúc dữ liệu", 18000000.0),
        Teacher(102, "nGô Thị áNH", 38, "Cơ sở dữ liệu", 23000000.0),
        Teacher(103, "Đặng Văn ĐỒNG", 45, "Trí tuệ nhân tạo")
    )




}