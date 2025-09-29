package com.example.summaytask12.system

import com.example.summaytask12.model.Course

class CoursesManager {
    private val courses = mutableListOf<Course>()

    fun addCourses(course: List<Course>) {
        courses.addAll(course)
    }

    fun displayAllCourses() {
        println("Danh sách môn học")
        // For each loop
        courses.forEach { course ->
            course.displayCourse()
            //  println("- ${course.courseName} (${course.courseId}, ${course.credit} tín chỉ)")
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

}