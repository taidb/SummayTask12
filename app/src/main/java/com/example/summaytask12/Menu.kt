package com.example.summaytask12

import com.example.summaytask12.system.ApplicationManager

suspend fun main() {
    val appManager = ApplicationManager()

    appManager.initialize()
    appManager.run()
}