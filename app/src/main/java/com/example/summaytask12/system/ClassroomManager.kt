package com.example.summaytask12.system

import com.example.summaytask12.enum.StatusSchedule
import com.example.summaytask12.extensions.capitalizeFirst
import com.example.summaytask12.generics.addItem
import com.example.summaytask12.generics.deleteItem
import com.example.summaytask12.generics.genericsPrint
import com.example.summaytask12.generics.printInfo
import com.example.summaytask12.generics.updateItem
import com.example.summaytask12.interfaces.BaseInterface
import com.example.summaytask12.model.Classroom
import com.example.summaytask12.model.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ClassroomManager : BaseInterface<Classroom> {
    private val classrooms = mutableListOf<Classroom>()

    override suspend fun getAll() {
        withContext(Dispatchers.IO) {
            delay(100) // Giả lập thời gian truy vấn
            genericsPrint("Danh sách tất cả phòng học (${classrooms.size} phòng):")
//            classrooms.forEach { classroom ->
//                genericsPrint(" - ${classroom.roomNumber} (ID: ${classroom.roomId}, Trạng thái: ${classroom.status})")
//            }
            printInfo(classrooms) { classroom ->
                " - ${classroom.roomNumber} (ID: ${classroom.roomId}, Trạng thái: ${classroom.status})"
            }
        }
    }

    override suspend fun getById(id: Int): Classroom? {
        return withContext(Dispatchers.IO) {
            delay(50) // Giả lập thời gian truy vấn
            classrooms.find { it.roomId == id }
        }
    }

    override suspend fun getByName(name: String): List<Classroom> {
        return withContext(Dispatchers.IO) {
            delay(50) // Giả lập thời gian truy vấn
            classrooms.filter { it.roomNumber.contains(name, ignoreCase = true) }
        }
    }

    override suspend fun insert(item: List<Classroom>) {
        withContext(Dispatchers.IO) {
            delay(200) // Giả lập thời gian insert
           // var addedCount = 0
//            item.forEach { classroom ->
//                if (classrooms.none { it.roomId == classroom.roomId }) {
//                    classrooms.add(classroom)
//                    addedCount++
//                }
//            }
            addItem(classrooms, item) { a, b -> a.roomId == b.roomId }
            println("Đã thêm ${item.size} phòng học mới.")
           // genericsPrint("Đã thêm $addedCount/${item.size} phòng học vào hệ thống")
        }
    }

    override suspend fun update(id: Int, item: Classroom) {
        withContext(Dispatchers.IO) {
            delay(100) // Giả lập thời gian update
//            val index = classrooms.indexOfFirst { it.roomId == id }
//            if (index != -1) {
//                classrooms[index] = item
//                genericsPrint("Đã cập nhật thông tin phòng học: ${item.roomNumber}")
//            } else {
//                genericsPrint("Không tìm thấy phòng học với ID: $id")
//            }
            updateItem(classrooms, id, item) { it.roomId }
        }
    }

    override suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            delay(100) // Giả lập thời gian delete
//            val classroom = classrooms.find { it.roomId == id }
//            if (classroom != null) {
//                classrooms.remove(classroom)
//                genericsPrint("Đã xóa phòng học: ${classroom.roomNumber}")
//            } else {
//                genericsPrint("Không tìm thấy phòng học với ID: $id")
//            }
            deleteItem(classrooms, id) { it.roomId }
        }
    }

//    fun addClassroom(classroom: Classroom) {
//        if (classrooms.none { it.roomId == classroom.roomId }) {
//            classrooms.add(classroom)
//            genericsPrint("Đã thêm phòng học: ${classroom.roomNumber}")
//        } else {
//            genericsPrint("Phòng học với ID ${classroom.roomId} đã tồn tại!")
//        }
//    }
//
//    fun getClassrooms(): List<Classroom> {
//        return classrooms.toList()
//    }

    fun getClassroomById(roomId: Int): Classroom? {
        return classrooms.find { it.roomId == roomId }
    }

    fun updateClassroomStatus(roomId: Int, status: StatusSchedule) {
        val classroom = getClassroomById(roomId)
        classroom?.status = status
    }

    // Higher-order function
    fun getClassroomsIsEmpty(condition: (Classroom) -> Boolean, action: (Classroom) -> Unit) {
        return classrooms.filter(condition).forEach(action)
    }

    fun emptyClassrooms(
        classrooms: List<Classroom>,
        transform: (Classroom) -> Boolean,
        condition: (Classroom) -> Boolean
    ): List<Classroom> {
        return classrooms.filter { transform(it) && condition(it) }
    }

    fun getAllClassrooms(): List<Classroom> {
        return classrooms.toList()
    }

//    fun getClassroomCount(): Int {
//        return classrooms.size
//    }
}