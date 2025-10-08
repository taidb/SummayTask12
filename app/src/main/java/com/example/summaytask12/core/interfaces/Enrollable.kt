package com.example.summaytask12.core.interfaces

import com.example.summaytask12.model.Course

interface Enrollable{
    fun enrollStudent( course: Course)
    fun listCourses()
}
