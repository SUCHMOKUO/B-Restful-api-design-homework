package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
}
