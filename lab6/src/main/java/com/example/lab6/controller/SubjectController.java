package com.example.lab6.controller;

import com.example.lab6.entity.Subject;
import com.example.lab6.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

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

    @GetMapping("/subjects")
    public ResponseEntity<?> getAllSubjects() {
        try {
            List<Subject> allSubjects = subjectService.getAllSubjects();
            if (allSubjects.isEmpty()) {
                return ResponseEntity.ok()
                        .body(new ApiResponse(true, "Список дисциплин пуст", allSubjects));
            }
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Дисциплины успешно получены", allSubjects));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при получении списка дисциплин: " + e.getMessage(), null));
        }
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<?> getSubject(@PathVariable("id") int id) {
        try {
            Subject subject = subjectService.getSubject(id);
            if (subject == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Дисциплина с ID " + id + " не найдена", null));
            }
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Дисциплина успешно найдена", subject));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при поиске дисциплины: " + e.getMessage(), null));
        }
    }

    @PostMapping("/subjects")
    public ResponseEntity<?> saveSubject(@RequestBody Subject subject) {
        try {
            if (subject.getName() == null || subject.getName().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse(false, "Имя дисциплины не может быть пустым", null));
            }
            if (subject.getCode() == null || subject.getCode().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse(false, "Код дисциплины не может быть пустым", null));
            }
            if (subject.getCredits() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse(false, "Количество кредитов должно быть больше 0", null));
            }

            Subject savedSubject = subjectService.saveSubject(subject);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "Дисциплина успешно создана", savedSubject));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при создании дисциплины: " + e.getMessage(), null));
        }
    }

    @PutMapping("/subjects")
    public ResponseEntity<?> updateSubject(@RequestBody Subject subject) {
        try {
            if (subject.getId() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse(false, "ID дисциплины должен быть указан для обновления", null));
            }

            Subject existingSubject = subjectService.getSubject(subject.getId());
            if (existingSubject == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Дисциплина с ID " + subject.getId() + " не найдена", null));
            }

            Subject updatedSubject = subjectService.saveSubject(subject);
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Дисциплина успешно обновлена", updatedSubject));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при обновлении дисциплины: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") int id) {
        try {
            Subject existingSubject = subjectService.getSubject(id);
            if (existingSubject == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "Дисциплина с ID " + id + " не найдена", null));
            }

            subjectService.deleteSubject(id);
            return ResponseEntity.ok()
                    .body(new ApiResponse(true, "Дисциплина с ID " + id + " успешно удалена", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Ошибка при удалении дисциплины: " + e.getMessage(), null));
        }
    }
}