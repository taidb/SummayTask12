package com.example.summaytask12.model

import com.example.summaytask12.core.enum.StatusSchedule

data class Classroom(
    val roomId: Int,
    val roomNumber: String,
    val capacity: Int,
    val facilities: Set<String> = emptySet(),
    var status : StatusSchedule

) {
    fun displayInfo() {
        println("Phòng $roomNumber (ID: $roomId), Sức chứa: $capacity người")
        if (facilities.isNotEmpty()) {
            println("Tiện nghi: ${facilities.joinToString()}")
            println("Trạng thái: ${status.name}")

        }
    }
}