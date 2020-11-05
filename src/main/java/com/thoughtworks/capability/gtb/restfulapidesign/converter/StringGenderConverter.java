package com.thoughtworks.capability.gtb.restfulapidesign.converter;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.springframework.core.convert.converter.Converter;

public class StringGenderConverter implements Converter<String, Student.Gender> {

    @Override
    public Student.Gender convert(String source) {
        return Student.Gender.valueOf(source.toUpperCase());
    }
}
