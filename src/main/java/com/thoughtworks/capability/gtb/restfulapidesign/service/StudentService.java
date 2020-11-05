package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Integer, StudentDto> students = new ConcurrentHashMap<>(16);
    private final AtomicInteger nextId = new AtomicInteger(1);

    public StudentDto createStudent(StudentDto studentDto) {
        Integer id = nextId.getAndIncrement();
        studentDto.setId(id);
        students.put(id, studentDto);
        return studentDto;
    }

    public void deleteStudent(Integer id) {
        students.remove(id);
    }

    public List<StudentDto> getAllStudents(StudentDto.Gender gender) {
        if (Objects.nonNull(gender)) {
            return students.values()
                    .stream()
                    .filter(studentDto -> studentDto.getGender().equals(gender))
                    .collect(Collectors.toList());
        }

        return new ArrayList<>(students.values());
    }
}
