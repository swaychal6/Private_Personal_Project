package com.jansuraksha.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T mapJsonToEntity(String jsonString, Class<T> entityClass) throws Exception {
        return objectMapper.readValue(jsonString, entityClass);
    }
    
    
    public static <T,D> D mapObjectToEntity(Object obj, Class<D> entityClass) throws Exception {
        return objectMapper.convertValue(obj, entityClass);
    }
}