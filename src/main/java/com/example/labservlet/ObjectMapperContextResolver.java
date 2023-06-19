package com.example.labservlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;


    @Provider
    public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

        private final ObjectMapper objectMapper;

        public ObjectMapperContextResolver() {
            objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.registerModule(new Jdk8Module());
           // objectMapper.registerModule(new EnumModule());
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);;
        }

        @Override
        public ObjectMapper getContext(Class<?> type) {
            return objectMapper;
        }
    }
