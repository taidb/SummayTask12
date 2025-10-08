package com.example.summaytask12.core.enum

enum class GradeLevel(val label: String, val minScore: Double, val maxScore: Double) {
    EXCELLENT("Xuất sắc", 3.6, 4.0),
    GOOD("Giỏi", 3.2, 3.5),
    AVERAGE("Khá", 2.5, 3.1),
    BELOW_AVERAGE("Trung bình", 2.0, 2.4),
    FAIL("Yếu", 0.0, 1.9);

}