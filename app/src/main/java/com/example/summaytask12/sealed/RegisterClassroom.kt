package com.example.summaytask12.sealed

sealed class RegisterClassroom {
    data class Success(val data: String) : RegisterClassroom()
    data class Error(val message: String) : RegisterClassroom()
    data object Loading : RegisterClassroom()
}