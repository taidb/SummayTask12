package com.example.summaytask12.system

import com.example.summaytask12.core.generics.deleteItem
import com.example.summaytask12.core.generics.measureExecutionTime
import com.example.summaytask12.core.generics.measureExecutionTimeInline
import com.example.summaytask12.core.generics.printInfo
import com.example.summaytask12.core.generics.updateItem
import com.example.summaytask12.core.interfaces.BaseInterface
import com.example.summaytask12.model.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class CoursesManager : BaseInterface<Course> {
    private val courses = mutableListOf<Course>()

    private fun addCourses(course: List<Course>) {
        courses.addAll(course)
    }

     fun getIdCourse(id: Int): Int?{
        val idCourse = courses.find { it.courseId ==id }
        return idCourse?.courseId
    }

    private fun displayAllCourses() {
        println("Danh sách môn học")
        // For each loop
//        courses.forEach { course ->
//            course.displayCourse()
//            //  println("- ${course.courseName} (${course.courseId}, ${course.credit} tín chỉ)")
//        }
        printInfo(courses) { course ->
            "Course: ${course.courseName}, Id: ${course.courseId}, Credit: ${course.credit}"
        }

    }

    fun getCourseStatistics(): Map<String, Any> {
        return mapOf(
            "total_courses" to courses.size,
            "total_credits" to courses.sumOf { it.credit },
            "advanced_courses" to courses.count { it.credit >= 3 },
            "average_credits" to if (courses.isNotEmpty()) courses.sumOf { it.credit } / courses.size else 0
        )
    }

    suspend fun creditsBySubject(courseId: Int, operation: (Course) -> Unit) {
        val course = getById(courseId)
        if (course != null) {
            operation(course)
        } else {
            println("Không tìm thấy môn học có ID: $courseId")
        }
    }

    //    fun creditFiltering(){
//        println("So sánh hiện năng khi sự dụng inline và khoong")
//        measureExecutionTime {
//            println("Lọc môn có tín chỉ lớn hơn 3")
//            val result=courses.filter { it.credit >3 }
//            println("Kết quả : ${result.map { it.courseName }}")
//        }
//        measureExecutionTimeInline {
//            println("Lọc môn có tín chỉ lớn hơn 3")
//            val result=courses.filter { it.credit >3 }
//            println("Kết quả : ${result.map { it.courseName }}")
//        }
//    }
    suspend fun creditFiltering() = coroutineScope {
        println("Lọc các môn học số tín chỉ > 3")
        val advancedCourses = courses.filter { it.credit > 3 }

        if (advancedCourses.isNotEmpty()) {
            println("Tìm thấy ${advancedCourses.size} môn học")
            advancedCourses.forEach {
                println("${it.courseName} (${it.credit} tín chỉ)")
            }
        } else {
            println("Không có môn học nào lớn hơn 3 tín.")
        }

        println("So sánh sự dụng inline và không: ")

        val iterations = 10_000

        val inlineJob = async(Dispatchers.Default) {
            measureExecutionTimeInline {
                for (i in 1..iterations) {
                    courses.filter { it.credit > 3 }
                }
            }
        }

        val normalJob = async(Dispatchers.Default) {
            measureExecutionTime {
                for (i in 1..iterations) {
                    courses.filter { it.credit > 3 }
                }
            }
        }
        // đồng thời chạy
        normalJob.await()
        inlineJob.await()
    }


    override suspend fun getAll() {
        displayAllCourses()
    }

    override suspend fun getById(id: Int): Course? {
        return courses.find { it.courseId == id }
    }

    override suspend fun getByName(name: String): List<Course> {
        return courses.filter { it.courseName.equals(name, ignoreCase = true) }
    }

    override suspend fun delete(id: Int) {
//        val removed = courses.removeIf { it.courseId == id }
//        if (removed) {
//            println("Đã xóa môn học có ID: $id")
//        } else {
//            println("Không tìm thấy môn học có ID: $id để xóa.")
//        }
        deleteItem(courses, id) { it.courseId}
    }

    override suspend fun update(id: Int, item: Course) {
//        val index = courses.indexOfFirst { it.courseId == id }
//        if (index != -1) {
//            courses[index] = item
//            println("Đã cập nhật môn học có ID: $id thành ${item.courseName}.")
//        } else {
//            println("Không tìm thấy môn học có ID: $id để cập nhật.")
//        }
        updateItem(courses, id, item) { it.courseId }
    }

    override suspend fun insert(item: List<Course>) {
        addCourses(item)
    }

}