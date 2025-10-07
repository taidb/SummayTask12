package com.example.summaytask12.util

class OutputHandler {
    fun displayMainStudentMenu() {
        println("\n=== HỆ THỐNG QUẢN LÝ SINH VIÊN TRƯỜNG HỌC ===")
        println("1. Thêm sinh viên")
        println("2. In danh sách sinh viên")
        println("3. Tạo báo cáo sinh viên cần tìm kiếm")
        println("4. Tổng học phí sinh viên")
        println("5. Thống kê sinh viên theo độ tuổi")
        println("6. Điểm GPA trung bình của sinh viên")
        println("7. Thêm sinh viên mẫu")
        println("8. Kiểm tra trùng ID")
        println("9. Danh sách xếp loại")
        println("10. Sinh viên đăng kí môn học")
        println("11. Tìm kiếm sinh viên theo địa chỉ")
        println("12. Cập nhật sinh viên")
        println("13. Tìm kiếm sinh viên theo tên")
        println("14. Điểm trung bình tất cả sinh viên")
        println("15.Sinh viên đỗ tốt nghiệp")
        println("16.Xóa sinh viên theo Id")
        println("17.Danh sách trạng thái sinh viên")
        println("18.5 Sinh viên đạt học bổng")
        println("19.Tổng số tỉn chỉ sinh viên")
        println("20.Kiểm tra tình trạng sinh viên : Học bổng ,Cảnh cáo")
        println("21. Kiểm tra snh viên có điểm lớn hơn 3.5")
        println("0. Thoát")
    }

    fun displayMenuSchool() {
        println("HỆ THỐNG QUẢN LÝ TRƯỜNG HỌC")
        println("1. Quản lý Phòng học")
        println("2. Quản lý Lịch học")
        println("3. Hủy đăng kí phòng học")
        println("4. Đăng kí phòng học")
        println("5. Kiểm tra lớp còn trống")
        println("6. Số phòng đang sử dụng")
        println("7. In danh sách môn học")
        println("8. Thống kê môn học")
        println("9. Số tín chỉ trên mỗi môn học")
        println("10. In môn học có tín chỉ lớn hơn 3")
        println("11. Cập nhật thông tin môn học")
        println("12. Xóa môn hoc theo Id")

        println("QUẢN LÝ PHÒNG HỌC (CRUD)")
        println("13. Xem tất cả phòng học")
        println("14. Tìm phòng học theo ID")
        println("15. Tìm phòng học theo tên")
        println("16. Xóa phòng học")
        println("17.Lớp học còn trống")

        println("QUẢN LÝ LỊCH HỌC")
        println("18. Xem tất cả lịch học")
        println("19. Tìm lịch học theo ID")
        println("20. Tìm lịch học theo tên")
        println("21. Xóa lịch học")
        println("0. Thoát")
    }

    fun displayMainTeacherMenu() {
        println("\n=== HỆ THỐNG QUẢN LÝ GIÁO VIÊN TRƯỜNG HỌC ===")
        println("1. In danh sách giáo viên")
        println("2. Tiền lương từng giáo viên")
        println("3. Lương giáo viên theo Id")
        println("4. Môn học giáo viên dạy")
        println("5. Kiểm tra đại học")
        println("6.Tìm kiếm giáo viên theo tên")
        println("7.Cập nhật thông tin thường gặp giáo viên")
        println("8. Cập nhật thông tin")
        println("9.Xóa giáo viên")
        println("0. Thoát")

    }

    fun displayMenu(){
        println("1.HỆ THỐNG QUẢN LÝ TRƯỜNG HỌC")
        println("2.HỆ THỐNG QUẢN LÝ GIÁO VIÊN TRƯỜNG HỌC")
        println("3.HỆ THỐNG QUẢN LÝ SINH VIÊN TRƯỜNG HỌC")
        println("0. Thoát")
        print("Nhập lựa chọn: ")
    }
}