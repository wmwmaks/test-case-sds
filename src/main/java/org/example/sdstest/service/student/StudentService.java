package org.example.sdstest.service.student;

import org.example.sdstest.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto getStudent(String id);
    List<StudentDto> getStudentsByIds(List<String> ids);
    StudentDto createStudent(StudentDto studentDto);
    void deleteStudent(String id);
    void updateStudent(String id, StudentDto studentDto);
}
