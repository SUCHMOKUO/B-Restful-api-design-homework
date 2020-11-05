package com.thoughtworks.capability.gtb.restfulapidesign.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private Gender gender;

    private String note;

    public enum Gender {
        MALE("male"),
        FEMALE("female");

        @JsonValue
        private final String value;

        Gender(String value) {
            this.value = value;
        }
    }
}
