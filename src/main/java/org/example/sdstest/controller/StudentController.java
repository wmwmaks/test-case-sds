package org.example.sdstest.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.sdstest.dto.StudentDto;
import org.example.sdstest.service.student.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public List<StudentDto> getStudents(@RequestBody List<String> ids) {
        return studentService.getStudentsByIds(ids);
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable @Positive String id) {
        return studentService.getStudent(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable @Positive String id) {
        studentService.deleteStudent(id);
    }

    @PutMapping
    public StudentDto createStudent(@RequestBody StudentDto student) {
        return studentService.createStudent(student);
    }

    @PostMapping("/{id}")
    public void updateStudent(@PathVariable @Positive String id, @RequestBody StudentDto student) {
        studentService.updateStudent(id, student);
    }
}
