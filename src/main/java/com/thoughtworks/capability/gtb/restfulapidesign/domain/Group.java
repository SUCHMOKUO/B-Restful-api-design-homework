package com.thoughtworks.capability.gtb.restfulapidesign.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    private Integer id;

    @NotEmpty
    private String name;

    private String note;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Student> students;

    public static Group copy(Group group) {
        return Group.builder()
                .id(group.getId())
                .name(group.getName())
                .note(group.getNote())
                .students(group.getStudents())
                .build();
    }
}
