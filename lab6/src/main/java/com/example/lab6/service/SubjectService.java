package com.example.lab6.service;

import com.example.lab6.entity.Subject;
import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();
    Subject saveSubject(Subject subject);
    Subject getSubject(int id);
    void deleteSubject(int id);
}