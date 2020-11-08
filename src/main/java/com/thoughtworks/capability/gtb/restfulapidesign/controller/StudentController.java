package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@NonNull @PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @GetMapping
    public List<Student> getAllStudents(@RequestParam(required = false) Student.Gender gender) {
        return studentService.getAllStudents(gender);
    }

    @GetMapping("/{id}")
    public Student getStudent(@NonNull @PathVariable Integer id) {
        return studentService.getStudent(id);
    }

    @PatchMapping("/{id}")
    public Student updateStudent(@NonNull @PathVariable Integer id,
                                 @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
}
