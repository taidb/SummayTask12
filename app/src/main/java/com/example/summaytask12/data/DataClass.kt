package com.example.summaytask12.data

import com.example.summaytask12.core.enum.StatusSchedule
import com.example.summaytask12.model.Classroom
import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Schedule
import com.example.summaytask12.model.Student
import com.example.summaytask12.model.Teacher

object DataClass {
    val courses = listOf(
        Course(101, "Lập trình Kotlin", 3),
        Course(102, "Cấu trúc dữ liệu", 4),
        Course(103, "Cơ sở dữ liệu", 3),
        Course(104, "Mạng máy tính", 3),
        Course(105, "Trí tuệ nhân tạo", 4)
    )

    //Tạo giáo viên
    val teachers = listOf(
        Teacher(101, "phạm thị d", 40, "Cấu trúc dữ liệu", 18000000.0),
        Teacher(102, "nGô Thị áNH", 38, "Cơ sở dữ liệu", 23000000.0),
        Teacher(103, "Đặng Văn ĐỒNG", 45, "Trí tuệ nhân tạo")
    )

    //Tạo sinh viên
    val students = listOf(
        Student(1, "Nguyễn Văn Anh", 20, 3.2, "Nghệ An"),
        Student(2, "Trần Thị Bình", 21, 3.6, "Hòa Bình"),
        Student(2, "Trần Thị Bình", 21, 3.6, "Hòa Bình"),
        Student(3, "Lê Văn Chí", 19, 2.8),
        Student(1, "Nguyễn Văn Tiến", 20, 3.2, "Ninh Bình"),
        Student(5, "Nguyễn Văn Hà", 18, 1.6, "Nghệ An"),
        Student(6, "Nguyễn Văn Hùng", 20, 4.0),
        Student(7, "Nguyễn Văn Lan", 17, 2.5),
        Student(8, "Nguyễn Văn Minh", 19, 2.4),
        Student(9, "Nguyễn Văn Nga", 21, 2.8),
        Student(10, "Nguyễn Văn Tú", 19, 3.5)
    )

    val classrooms = listOf(
        Classroom(
            110,
            "101",
            50,
            setOf("Máy chiếu", "Máy lạnh", "WiFi"),
            StatusSchedule.SCHEDULED
        ),
        Classroom(111, "102", 40, setOf("Máy chiếu", "Máy lạnh"), StatusSchedule.SCHEDULED),
        Classroom(
            112,
            "201",
            60,
            setOf("Máy tính", "Máy chiếu", "Máy lạnh", "WiFi"),
            StatusSchedule.CANCELED
        ),
        Classroom(113, "202", 30, setOf("Máy chiếu"), StatusSchedule.CANCELED),
        Classroom(
            114,
            "203",
            20,
            setOf("Máy tính", "Thiết bị thí nghiệm", "Máy lạnh"),
            StatusSchedule.DRUM
        )
    )

    val sampleSchedule = listOf(
        Schedule(
            1,
            courses[0], teachers[0], classrooms[0], 2, "07:30", "09:00"
        ),
        Schedule(
            2,
            courses[1], teachers[0], classrooms[1], 3, "09:30", "11:00"
        ),
        Schedule(
            3,
            courses[2], teachers[1], classrooms[2], 4, "13:30", "15:00"
        )
    )
    val sampleSchedule2= Schedule(4, courses[2], teachers[1], classrooms[3],5,"13:30", "15:00")


}