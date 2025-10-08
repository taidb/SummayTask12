package com.example.summaytask12.core.interfaces

interface BaseInterface<T> {
    suspend fun getAll()
    suspend fun getById(id:Int):T?
    suspend fun getByName(name:String):List<T>
    suspend fun insert(item:List<T>)
    suspend fun update(id:Int,item:T)
    suspend fun delete(id:Int)
}