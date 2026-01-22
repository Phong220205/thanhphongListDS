package com.example.lab3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; // Import gọn lại

import com.example.lab3.service.StudentService;
import com.example.lab3.model.Student;

@RestController
@RequestMapping("/api/students")
@CrossOrigin 
public class StudentController {

    @Autowired
    private StudentService service;

    // 1. API thêm sinh viên (SỬA LẠI: Dùng @RequestBody)
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        // Khi thêm mới, ID sẽ null, Database tự tạo
        return service.addStudent(student);
    }

    // 2. API xóa sinh viên
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return "Student with ID " + id + " has been deleted.";
    }

    // 3. Tim kiếm sinh viên theo tên
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return service.findByName(name);
    }

    // 4. API lấy sinh viên theo ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    // 5. API lấy danh sách sinh viên
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }
    
    // 6. API cập nhật sinh viên (SỬA LẠI: Dùng @RequestBody cho chuẩn)
    @PostMapping("/update/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Student existingStudent = service.getStudentById(id);
        if (existingStudent != null) {
            existingStudent.setName(studentDetails.getName());
            existingStudent.setEmail(studentDetails.getEmail());
            return service.addStudent(existingStudent); // Hàm save sẽ tự hiểu là update nếu có ID
        }
        return null;
    }
}