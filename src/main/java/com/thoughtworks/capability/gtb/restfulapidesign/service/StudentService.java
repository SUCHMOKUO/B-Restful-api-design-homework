package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotFoundException;
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

    private final Map<Integer, Student> students = new ConcurrentHashMap<>(16);
    private final AtomicInteger nextId = new AtomicInteger(1);

    public Student createStudent(Student student) {
        Integer id = nextId.getAndIncrement();
        student.setId(id);
        students.put(id, student);
        return student;
    }

    public void deleteStudent(Integer id) {
        students.remove(id);
    }

    public List<Student> getAllStudents(Student.Gender gender) {
        if (Objects.nonNull(gender)) {
            return students.values()
                    .stream()
                    .filter(student -> student.getGender().equals(gender))
                    .collect(Collectors.toList());
        }

        return new ArrayList<>(students.values());
    }

    private synchronized Student checkAndGetStudent(Integer id) {
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException();
        }

        return students.get(id);
    }

    public Student getStudent(Integer id) {
        return checkAndGetStudent(id);
    }

    public Student updateStudent(Integer id, Student student) {
        Student studentToUpdate = checkAndGetStudent(id);

        if (Objects.nonNull(student.getName())) {
            studentToUpdate.setName(student.getName());
        }

        if (Objects.nonNull(student.getGender())) {
            studentToUpdate.setGender(student.getGender());
        }

        if (Objects.nonNull(student.getNote())) {
            studentToUpdate.setNote(student.getNote());
        }

        return studentToUpdate;
    }
}
