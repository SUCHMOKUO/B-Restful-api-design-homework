package com.thoughtworks.capability.gtb.restfulapidesign.converter;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentDto;
import org.springframework.core.convert.converter.Converter;

public class StringGenderConverter implements Converter<String, StudentDto.Gender> {

    @Override
    public StudentDto.Gender convert(String source) {
        return StudentDto.Gender.valueOf(source.toUpperCase());
    }
}
