package org.example.sdstest.mapper;

import org.example.sdstest.dto.StudentDto;
import org.example.sdstest.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

    Student toStudent(StudentDto studentDto);

    StudentDto toStudentDto(Student student);

    List<Student> toStudents(List<StudentDto> studentDtos);

    List<StudentDto> toStudentDtos(List<Student> students);
}
