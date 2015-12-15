package com.bean.copy;

import com.bean.copy.annotation.Copy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
class BeanCopier {


    void beanCopy(final Object copyFrom, final Object copyTo) {
        Field[] fields = copyFrom.getClass().getDeclaredFields();
        for(Field field : fields) {
            Copy copy = field.getAnnotation(Copy.class);
            if( copy != null) {
                addValueToField(copyTo, field, copy.copyTo());
            } else {
                addValueToField(copyTo, field, copy.copyTo());
            }
        }


    }

    private void addValueToField(Object copyTo, Field field, String name) {
        try {
            Field copyToField = copyTo.getClass().getField(name);
            copyToField.set(copyTo, field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
