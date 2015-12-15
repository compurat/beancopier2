package com.bean.copy;

import com.bean.copy.annotation.Copy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class BeanCopier {


    public void beanCopy(final Object copyFrom, final Object copyTo) {
        Field[] fields = copyFrom.getClass().getDeclaredFields();
        for(Field field : fields) {
            Copy copy = field.getAnnotation(Copy.class);
            if( copy != null) {
                addValueToField(copyFrom,copyTo, field, copy.copyTo());
            } else {
                addValueToField(copyFrom, copyTo, field, field.getName());
            }
        }
    }

    private void addValueToField(final Object copyFrom, final Object copyTo, final Field field, final String name) {
        try {
            Field copyToField = copyTo.getClass().getDeclaredField(name);
            copyToField.setAccessible(true);
            field.setAccessible(true);
            copyToField.set(copyTo, field.get(copyFrom));
            copyToField.setAccessible(false);
            field.setAccessible(false);
        } catch (NoSuchFieldException e) {
            log.error("could not find field " + name);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            log.error("could not access field " + name);
            e.printStackTrace();
        }
    }
}
