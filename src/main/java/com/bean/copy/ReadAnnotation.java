package com.bean.copy;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.bean.copy.annotation.Copy;

class ReadAnnotation {

    Map<String,String> readValue(Object bean) {
        Map<String,String> fieldToFieldMapper = new HashMap<String, String>();
        Field[] fields =  bean.getClass().getDeclaredFields();
        for(Field field : fields) {
            Copy copy = field.getAnnotation(Copy.class);
            if(copy == null) {
                fieldToFieldMapper.put(field.getName(),field.getName());
            } else {
                fieldToFieldMapper.put(field.getName(),copy.copyTo());
            }

        }
        return fieldToFieldMapper;
    }
}
