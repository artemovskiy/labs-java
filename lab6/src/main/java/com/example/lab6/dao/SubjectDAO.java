package com.example.lab6.dao;

import com.example.lab6.entity.Subject;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubjectDAO {
    List<Subject> getAllSubjects();
    Subject saveSubject(Subject subject);
    Subject getSubject(int id);
    void deleteSubject(int id);
}
