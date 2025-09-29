package com.example.summaytask12.model

abstract class UniversityMember(id: Int, name: String, age: Int) :
    Person(id, name, age) {
    abstract fun getRole(): String
    open fun showInfo() {
        println("ID: $id")
        println("Name: $name")
        println("Age: $age")
    }

    override fun showInfoName() {
        println("Tên là $name và năm sinh là $age")
    }


}