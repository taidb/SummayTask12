package com.example.summaytask12.system

import com.example.summaytask12.data.DataInitializer
import com.example.summaytask12.menu.MenuClassroomHandler
import com.example.summaytask12.menu.MenuStudentHandler
import com.example.summaytask12.menu.MenuTeacherHandler
import com.example.summaytask12.utils.InputHandler
import com.example.summaytask12.utils.OutputHandler

class ApplicationManager {
    private val coursesManager = CoursesManager()
    private val schoolManager = SchoolManager()
    private val studentsManager = StudentsManager()
    private val teachersManager = TeachersManager()
    private val outputHandler = OutputHandler()
    private val dataInitializer = DataInitializer(coursesManager, schoolManager, studentsManager, teachersManager)

    private val menuStudentHandler = MenuStudentHandler(studentsManager, outputHandler)
    private val menuTeacherHandler = MenuTeacherHandler(teachersManager, outputHandler)
    private val menuClassroomsHandler = MenuClassroomHandler(coursesManager, schoolManager, outputHandler)

    suspend fun initialize() {
        dataInitializer.initializeAllData()
    }

   suspend fun run() {
        var selection: Int
        do {
            outputHandler.displayMenu()
            selection = InputHandler.getMenuSelection()
            handleMenuSelection(selection)
        } while (selection != 0)
    }

    private suspend fun handleMenuSelection(selection: Int) {
        when (selection) {
            1 -> menuClassroomsHandler.handleSelection()
            2 -> menuTeacherHandler.handleSelection()
            3 -> menuStudentHandler.handleSelection()
            0 -> println("Thoát chương trình...")
            else -> println("Lựa chọn không hợp lệ")
        }
    }
}