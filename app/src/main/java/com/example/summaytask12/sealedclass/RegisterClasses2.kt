package com.example.summaytask12.sealedclass

import com.example.summaytask12.model.Course
import com.example.summaytask12.model.Student
import com.example.summaytask12.model.Teacher

sealed class RegisterClasses2 {
    data class CourseAdded(val course: Course) : RegisterClasses2()
    data class StudentEnrolled(val student: Student, val course: Course) : RegisterClasses2()
    data class TeacherHired(val teacher: Teacher) : RegisterClasses2()
    data class GradeAssigned(val student: Student, val course: Course, val grade: Double) : RegisterClasses2()
}