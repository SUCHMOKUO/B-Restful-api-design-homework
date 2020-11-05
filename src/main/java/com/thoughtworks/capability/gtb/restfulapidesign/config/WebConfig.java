package com.thoughtworks.capability.gtb.restfulapidesign.config;

import com.thoughtworks.capability.gtb.restfulapidesign.converter.StringGenderConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringGenderConverter());
    }
}
