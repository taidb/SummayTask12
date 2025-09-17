package com.example.summaytask12.utils

import com.example.summaytask12.model.Teacher

// let: Dùng để biến đổi giá trị hoặc xử lý an toàn với null
fun demoLet(teachers: List<Teacher>) {
    for (i in teachers.indices) {
        var salary = teachers[i]?.let {
            it.salary
        } ?: "Không hiện thị lương"
        println("Lương giáo viên ${teachers[i].name} là $salary")

    }
}

// apply: Dùng để cấu hình đối tượng (thường dùng khi khởi tạo)
fun demoApply(teacher: Teacher) {
    var user = teacher.apply {
        salary = 12300000.0
    }
    println("Giáo viên sau khi apply: $user")
}

// run: Dùng khi muốn thực hiện một khối lệnh và trả về kết quả
fun demoRun(teacher: Teacher) {
    var result = teacher.run {
        println("Tên : $name, Tuổi : $age , Dạy môn : ${subject}")
        subject="Hệ điều hành"  // giá trị trả về của run
        salary =20000000.0
    }
    println("Kết quả run: $result")
}

//with: Dùng khi muốn gọi nhiều hàm trên cùng một đối tượng (truyền đối tượng làm tham số)
fun demoWith(teacher: Teacher) {
    val withResult = with(teacher) {
        println("Tên : $name, Tuổi : $age")
        age + 1 // kết quả trả về
    }
    println("Kết quả with: $withResult")
}

// also: Dùng khi muốn thực hiện tác vụ phụ (logging, debug) mà không thay đổi đối tượng
fun demoAlso(teacher: Teacher) {
    var user = teacher.also {
        println("Tên: ${it.name}, Tuổi: ${it.age}") // tác vụ phụ
    }
    println("User sau khi also: $user")
}
