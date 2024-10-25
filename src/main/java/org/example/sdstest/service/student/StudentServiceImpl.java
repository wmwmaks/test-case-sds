package org.example.sdstest.service.student;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sdstest.dto.StudentDto;
import org.example.sdstest.entity.Student;
import org.example.sdstest.mapper.StudentMapper;
import org.example.sdstest.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto getStudent(String id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Student with id %s not found".formatted(id)));
        log.debug("Student with id %s found".formatted(id));
        return studentMapper.toStudentDto(student);
    }

    @Override
    public List<StudentDto> getStudentsByIds(List<String> ids) {
        List<Student> students = studentRepository.findAllById(ids);
        List<String> foundStudentIds = students.stream().map(Student::getId).toList();
        List<String> notFoundStudentIds = ids.stream()
                .filter(studentId -> !foundStudentIds.contains(studentId))
                .toList();
        if (!notFoundStudentIds.isEmpty()) {
            log.debug("Users with ids %s not found"
                    .formatted(String.join(", ", notFoundStudentIds.stream().map(Object::toString).toList())));
        }
        return studentMapper.toStudentDtos(students);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        studentRepository.save(studentMapper.toStudent(studentDto));
        log.info("Student %s %s %s created".formatted(studentDto.firstName(),
                studentDto.middleName(),
                studentDto.lastName()));
        return studentDto;
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
        log.info("Student with id %s deleted".formatted(id));
    }

    @Override
    public void updateStudent(String id, StudentDto studentDto) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student with id %s not found".formatted(id));
        }
        Student studentToUpdate = studentMapper.toStudent(studentDto);
        studentToUpdate.setId(id);
        studentRepository.save(studentToUpdate);
        log.info("Student with id %s updated".formatted(id));
    }
}