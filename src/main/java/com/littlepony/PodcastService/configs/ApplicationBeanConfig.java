package com.littlepony.PodcastService.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfig {

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
