package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final List<Group> groups = new ArrayList<>(6);
    private final StudentService studentService;

    public GroupService(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostConstruct
    private void initGroups() {
        for (int i = 1; i <= 6; i++) {
            groups.add(Group.builder()
                    .id(i)
                    .name("未命名组")
                    .build());
        }
    }

    private Group checkAndGetGroup(Integer id) {
        if (id < 1 || id > 6) {
            throw new GroupNotFoundException();
        }

        return groups.get(id - 1);
    }

    public Group updateGroup(Integer id, Group group) {
        Group groupToUpdate = checkAndGetGroup(id);

        groupToUpdate.setName(group.getName());

        return groupToUpdate;
    }

    private List<List<Student>> createStudentLists() {
        List<List<Student>> studentLists = new ArrayList<>(6);

        for (int i = 0; i < 6; i++) {
            studentLists.add(new ArrayList<>(16));
        }

        return studentLists;
    }

    public List<Group> regroupAndGetAll() {
        List<Student> students = studentService.getAllStudents(null);
        int totalStudent = students.size();
        int restStudent = totalStudent % 6;

        Collections.shuffle(students);
        List<List<Student>> studentLists = createStudentLists();

        for (int groupIndex = 0; groupIndex < restStudent; groupIndex++) {
            studentLists.get(groupIndex).add(students.remove(0));
        }

        int restStudentAmountEachGroup = (totalStudent - restStudent) / 6;

        for (int groupIndex = 0; groupIndex < 6; groupIndex++) {
            for (int i = 0; i < restStudentAmountEachGroup; i++) {
                studentLists.get(groupIndex).add(students.remove(0));
            }
        }

        return groups.stream()
                .peek((group) -> group.setStudents(studentLists.get(group.getId() - 1)))
                .collect(Collectors.toList());
    }

    public List<Group> getAllGroups() {
        return groups;
    }
}
