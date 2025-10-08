package com.example.summaytask12.core.sealed

sealed class StatusStudent {
    data class Scholarship(val message:String) : StatusStudent()
    data class Warning(val message:String): StatusStudent()
    data class Normal(val message:String): StatusStudent()
}