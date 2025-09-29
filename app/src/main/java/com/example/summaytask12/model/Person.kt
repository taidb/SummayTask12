package com.example.summaytask12.model


open class Person(val id:Int ,val name:String ,val age :Int) {
    open fun showInfoName() {
        println("Tên là $name")
    }

    open fun showSubject(subject: String) =
        println("Môn học là $subject")

}