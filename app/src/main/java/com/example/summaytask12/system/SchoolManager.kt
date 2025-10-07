package com.example.summaytask12.system

import com.example.summaytask12.enum.StatusSchedule
import com.example.summaytask12.model.Classroom
import com.example.summaytask12.model.Schedule


class SchoolManager {
    private val classroomsManager = ClassroomManager()
    private val schedulesManager = SchedulesManager(classroomsManager)

    fun getClassrooms(): List<Classroom> = classroomsManager.getAllClassrooms()
    fun getClassroomsIsEmpty(condition: (Classroom) -> Boolean, action: (Classroom) -> Unit) =
        classroomsManager.getClassroomsIsEmpty(condition, action)

    fun emptyClassrooms(classrooms: List<Classroom>, capacity: Int) =
        classroomsManager.emptyClassrooms(
            classrooms,
            transform = { it.capacity > capacity },
            condition = { it.status == StatusSchedule.CANCELED || it.status == StatusSchedule.DRUM }
        )

    suspend fun getAllClassrooms() = classroomsManager.getAll()
    suspend fun getClassroomById(id: Int) = classroomsManager.getById(id)
    suspend fun getClassroomByName(name: String) = classroomsManager.getByName(name)
    suspend fun insertClassrooms(classrooms: List<Classroom>) = classroomsManager.insert(classrooms)
    suspend fun deleteClassroom(id: Int) = classroomsManager.delete(id)

    fun cancelSchedule(scheduleId: Int) = schedulesManager.cancelSchedule(scheduleId)

    suspend fun getAllSchedules() = schedulesManager.getAll()
    suspend fun getScheduleById(id: Int) = schedulesManager.getById(id)
    suspend fun getScheduleByName(name: String) = schedulesManager.getByName(name)
    suspend fun insertSchedules(schedules: List<Schedule>) = schedulesManager.insert(schedules)
    suspend fun deleteSchedule(id: Int) = schedulesManager.delete(id)

    suspend fun fetchSchedulesAsync(): List<Schedule> = schedulesManager.fetchSchedulesAsync()
    suspend fun createScheduleAsync(schedule: Schedule) =
        schedulesManager.createScheduleAsync(schedule)

    suspend fun calculateScheduledRoomsAsync(): Int =
        schedulesManager.calculateScheduledRoomsAsync()

    companion object {
        const val MIN_GPA_FOR_GRADUATION = 2.0
    }
}