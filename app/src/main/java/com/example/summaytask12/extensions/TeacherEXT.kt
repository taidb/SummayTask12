package com.example.summaytask12.extensions

import com.example.summaytask12.model.Teacher


// Extension functions cho Teacher
fun Teacher.calculateAnnualSalary(bonus: Double = 0.0): Double {
    return (this.salary ?: 0.0) * 12 + bonus
}



