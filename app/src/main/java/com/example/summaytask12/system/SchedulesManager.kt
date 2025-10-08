package com.example.summaytask12.system

import com.example.summaytask12.core.enum.StatusSchedule
import com.example.summaytask12.core.generics.genericsPrint
import com.example.summaytask12.core.generics.printInfo
import com.example.summaytask12.core.interfaces.BaseInterface
import com.example.summaytask12.model.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SchedulesManager(private val classroomsManager: ClassroomManager) : BaseInterface<Schedule> {
    private val schedules = mutableListOf<Schedule>()

    override suspend fun getAll() {
        withContext(Dispatchers.IO) {
            delay(150) // Giả lập thời gian truy vấn
            genericsPrint("Danh sách tất cả lịch học (${schedules.size} lịch):")
            printInfo(schedules) { schedule ->
                " - ${schedule.course.courseName} (ID: ${schedule.id}) - GV: ${schedule.teacher.name} - Phòng: ${schedule.classroom.roomNumber}"
            }
        }
    }

    override suspend fun getById(id: Int): Schedule? {
        return withContext(Dispatchers.IO) {
            delay(50) // Giả lập thời gian truy vấn
            schedules.find { it.id == id }
        }
    }

    override suspend fun getByName(name: String): List<Schedule> {
        return withContext(Dispatchers.IO) {
            delay(50) // Giả lập thời gian truy vấn
            schedules.filter {
                it.course.courseName.contains(name, ignoreCase = true) ||
                        it.teacher.name.contains(name, ignoreCase = true)
            }
        }
    }

    override suspend fun insert(item: List<Schedule>) {
        withContext(Dispatchers.IO) {
            delay(300) // Giả lập thời gian insert
            var addedCount = 0
            item.forEach { schedule ->
                if (createScheduleInternal(schedule)) {
                    addedCount++
                }
            }
            genericsPrint("Đã thêm $addedCount/${item.size} lịch học vào hệ thống")
        }
    }

    override suspend fun update(id: Int, item: Schedule) {
        withContext(Dispatchers.IO) {
            delay(150) // Giả lập thời gian update
            val index = schedules.indexOfFirst { it.id == id }
            if (index != -1) {
                // Hủy lịch cũ
                cancelScheduleInternal(id)
                // Tạo lịch mới
                if (createScheduleInternal(item)) {
                    genericsPrint("Đã cập nhật lịch học ID: $id")
                } else {
                    genericsPrint("Không thể cập nhật lịch học ID: $id")
                }
            } else {
                genericsPrint("Không tìm thấy lịch học với ID: $id")
            }
        }
    }

    override suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            delay(100) // Giả lập thời gian delete
            cancelSchedule(id)
        }
    }

//    fun addSchedules(schedule: List<Schedule>) {
//        schedules.addAll(schedule)
//    }

    private fun createScheduleInternal(scheduleRequest: Schedule): Boolean {
        val existingClassroom = classroomsManager.getClassroomById(scheduleRequest.classroom.roomId)
        if (existingClassroom == null) {
            genericsPrint("${scheduleRequest.classroom.roomNumber} không tồn tại trong hệ thống.")
            return false
        }

        if (scheduleRequest.teacher.subject != scheduleRequest.course.courseName) {
            genericsPrint("Giáo viên ${scheduleRequest.teacher.name} không được phân công dạy môn ${scheduleRequest.course.courseName}.")
            return false
        }

        if (existingClassroom.status == StatusSchedule.SCHEDULED) {
            genericsPrint("Phòng đã có người đăng kí")
            return false
        }

        val conflictingSchedule = schedules.firstOrNull {
            it.classroom.roomId == existingClassroom.roomId &&
                    it.classroom.status == StatusSchedule.SCHEDULED
        }

        if (conflictingSchedule != null) {
            genericsPrint(
                "Phòng ${existingClassroom.roomNumber} đã được xếp lịch cho môn " +
                        "${conflictingSchedule.course.courseName} (GV: ${conflictingSchedule.teacher.name}) " +
                        "vào Thứ ${scheduleRequest.dayOfWeek + 1}, lúc ${scheduleRequest.startTime}."
            )
            return false
        }

        classroomsManager.updateClassroomStatus(existingClassroom.roomId, StatusSchedule.SCHEDULED)

        val newScheduleEntry = scheduleRequest.copy(
            classroom = existingClassroom
        )

        schedules.add(newScheduleEntry)
        genericsPrint(
            "Đã tạo lịch học mới thành công: " +
                    "${newScheduleEntry.teacher.name} - ${newScheduleEntry.course.courseName} " +
                    "- Phòng ${newScheduleEntry.classroom.roomNumber}" +
                    " (Trạng thái phòng: ${newScheduleEntry.classroom.status})"
        )
        return true
    }

    private fun findScheduleById(id: Int): Schedule? {
        return schedules.find { it.id == id }
    }

    fun cancelSchedule(scheduleId: Int) {
        val schedule = findScheduleById(scheduleId)
        if (schedule != null) {
            schedules.remove(schedule)
            classroomsManager.updateClassroomStatus(schedule.classroom.roomId, StatusSchedule.CANCELED)
            genericsPrint("Đã hủy lịch học cho môn ${schedule.course.courseName}")
        } else {
            genericsPrint("Không tìm thấy lịch học với ID: $scheduleId")
        }
    }

    private fun cancelScheduleInternal(scheduleId: Int) {
        cancelSchedule(scheduleId)
    }


//    fun getSchedules(): List<Schedule> {
//        return schedules.toList()
//    }

    // Coroutine functions
    suspend fun fetchSchedulesAsync(): List<Schedule> = withContext(Dispatchers.IO) {
        delay(1000) // ví dụ đang sự dụng để call api
        println("Đã tải ${schedules.size} lịch học từ CSDL")
        schedules
    }

    suspend fun createScheduleAsync(schedule: Schedule) = coroutineScope {
        launch(Dispatchers.IO) {
            delay(500) // ví dụ đây đang đọc file hoặc truy cập DB
            createScheduleInternal(schedule)
        }
    }

    suspend fun calculateScheduledRoomsAsync(): Int = coroutineScope {
        val scheduledCount = async(Dispatchers.Default) {
            schedules.count { it.classroom.status == StatusSchedule.SCHEDULED }
        }
        scheduledCount.await()
    }

}