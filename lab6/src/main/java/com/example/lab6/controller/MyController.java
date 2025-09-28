package com.example.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lab6.entity.Student;
import com.example.lab6.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() {
        try {
            List<Student> allStudents = studentService.getAllStudents();
            if (allStudents.isEmpty()) {
                return ResponseEntity.ok()
                        .body(new ApiResponse(true, "Список студентов пуст", allStudents));
            }
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Студенты успешно получены", allStudents));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при получении списка студентов: " + e.getMessage(), null));
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
        try {
            Student student = studentService.getStudent(id);
            if (student == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Студент с ID " + id + " не найден", null));
            }
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Студент успешно найден", student));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при поиске студента: " + e.getMessage(), null));
        }
    }

    @PostMapping("/students")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        try {
            if (student.getName() == null || student.getName().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse(false, "Имя студента не может быть пустым", null));
            }

            Student savedStudent = studentService.saveStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "Студент успешно создан", savedStudent));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при создании студента: " + e.getMessage(), null));
        }
    }

    @PutMapping("/students")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        try {
            if (student.getId() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse(false, "ID студента должен быть указан для обновления", null));
            }

            Student existingStudent = studentService.getStudent(student.getId());
            if (existingStudent == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Студент с ID " + student.getId() + " не найден", null));
            }

            Student updatedStudent = studentService.saveStudent(student);
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Студент успешно обновлен", updatedStudent));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при обновлении студента: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        try {
            Student existingStudent = studentService.getStudent(id);
            if (existingStudent == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Студент с ID " + id + " не найден", null));
            }

            studentService.deleteStudent(id);
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Студент с ID " + id + " успешно удален", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при удалении студента: " + e.getMessage(), null));
        }
    }

    public static class ApiResponse {
        private boolean success;
        private String message;
        private Object data;

        public ApiResponse(boolean success, String message, Object data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}