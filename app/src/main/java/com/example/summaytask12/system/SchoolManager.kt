package com.example.summaytask12.system

import com.example.summaytask12.enum.StatusSchedule
import com.example.summaytask12.generics.genericsPrint
import com.example.summaytask12.model.Classroom
import com.example.summaytask12.model.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SchoolManager {

    private val schedules = mutableListOf<Schedule>()
    private val classrooms = mutableListOf<Classroom>()

    fun addClassroom(classroom: Classroom) {
        if (classrooms.none { it.roomId == classroom.roomId }) {
            classrooms.add(classroom)
            genericsPrint("Đã thêm phòng học: ${classroom.roomNumber}")
        } else {
            genericsPrint("Phòng học với ID ${classroom.roomId} đã tồn tại!")
        }
    }

    // Lấy danh sách phòng học
    fun getClassrooms(): List<Classroom> {
        return DataClass.classrooms
    }

    fun addSchedules(schedule: List<Schedule>) {
        schedules.addAll(schedule)
    }

    private fun createSchedule(scheduleRequest: Schedule) {
        val existingClassroom =
            classrooms.find { it.roomId == scheduleRequest.classroom.roomId  }
        if (existingClassroom == null) {
            genericsPrint("${scheduleRequest.classroom.roomNumber} không tồn tại trong hệ thống.")
            return
        }

        if (scheduleRequest.teacher.subject != scheduleRequest.course.courseName) {
            genericsPrint("Giáo viên ${scheduleRequest.teacher.name} không được phân công dạy môn ${scheduleRequest.course.courseName}.")
            return
        }
        if (scheduleRequest.classroom.status == StatusSchedule.SCHEDULED) {
            genericsPrint("Phòng đã có người đăng kí")
            return
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
            return
        }
        existingClassroom.status = StatusSchedule.SCHEDULED

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
    }

    private fun findScheduleById(id: Int): Schedule? {
        return schedules.find { it.id == id }
    }

    fun cancelSchedule(scheduleId: Int) {
        val schedule = findScheduleById(scheduleId)
        if (schedule != null) {
            schedules.remove(schedule)
            schedule.classroom.status = StatusSchedule.CANCELED
            genericsPrint("Đã hủy lịch học cho môn ${schedule.course.courseName}")
        } else {
            genericsPrint("Không tìm thấy sinh viên với ID: $scheduleId")
        }
    }

    // Lấy danh sách lịch học
//    fun getSchedules(): List<Schedule> {
//        return schedules
//    }


    // Higher-order function
    fun getClassroomsIsEmpty(condition: (Classroom) -> Boolean, action: (Classroom) -> Unit) {
        return classrooms.filter(condition).forEach(action)
    }

    //giả lập để sử dụng couroutine :
    suspend fun fetchSchedulesAsync():List<Schedule> = withContext(Dispatchers.IO){
        delay(1000) // ví dụ đang sự dụng để call api
        println("Đã tải ${schedules.size} lịch học từ CSDL")
        schedules
    }

    fun createScheduleAsync(scope: CoroutineScope,schedule: Schedule){
        scope.launch(Dispatchers.IO) {
            delay(500) // ví dụ đây đang đọc file hoặc truy cập DB
            createSchedule(schedule)
        }
    }

    suspend fun calculateScheduledRoomsAsync(): Int = coroutineScope {
        val scheduledCount = async(Dispatchers.Default) {
            schedules.count { it.classroom.status == StatusSchedule.SCHEDULED }
        }
        scheduledCount.await()
    }

//    fun getClassroomsIsEmpty() {
//        println(classrooms.filter {(it.status == StatusSchedule.DRUM || it.status == StatusSchedule.CANCELED) })
//    }

    // Hàm một dòng
    //  fun isUniversityEmpty(): Boolean = students.isEmpty() && teachers.isEmpty() && courses.isEmpty()


//    fun updateTeacherSalary(teacherId: Int, newSalary: Double?): Boolean {
//        val teacher = teachers.firstOrNull { it.id == teacherId }
//
//        // Sử dụng safe call
//        teacher?.salary = newSalary
//        return teacher != null
//    }


    //When
//    fun studentClassification(students: List<Student>) {
//        println("Danh sách sinh viên theo xếp loại")
//        for (student in students) {
//            when (student.gpa) {
//                in 0.0..1.5 -> println("Sinh viên ${student.name} xếp loại yếu")
//                in 1.6..2.0 -> println("Sinh viên ${student.name} xếp loại dưới trung bình")
//                in 2.1..3.0 -> println("Sinh viên ${student.name} xếp loại trung bình")
//                in 3.1..3.5 -> println("Sinh viên ${student.name} xếp loại khá")
//                in 3.6..4.0 -> println("Sinh viên ${student.name} xếp loại giỏi")
//                else -> println("Điểm GPA không hợp lệ")
//            }
//        }
//    }

    companion object {
        const val MIN_GPA_FOR_GRADUATION = 2.0
    }
}
