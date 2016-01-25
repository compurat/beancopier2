package com.bean.copy;

import com.bean.copy.annotation.Copy;
import com.bean.copy.annotation.IgnoreCopy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * this class converts one bean to another.
 */
@Slf4j
public class BeanCopier {


    /**
     * This method detects if the fieldnames are the same or from the {@link Copy} annotation.
     * After that it calls the addValueToField method and copies the values from one bean to the other.
     * @param copyFrom the bean that contains the values.
     * @param copyTo the bean where the values should be copied to.
     */
    public void beanCopy(final Object copyFrom, final Object copyTo) {
        Field[] fields = copyFrom.getClass().getDeclaredFields();
        for(Field field : fields) {
            Copy copy = field.getAnnotation(Copy.class);
            IgnoreCopy ignoreCopy = field.getAnnotation(IgnoreCopy.class);
            String fieldName = null;
            if (copy != null) {
                fieldName = copy.copyTo();
            } else {
                fieldName = field.getName();
            }
            boolean ignoreCopyFromCopyTo = ignoreCopyToField(copyTo, fieldName);
            checkAndAddValues(copyFrom, copyTo, field, copy, ignoreCopy, ignoreCopyFromCopyTo);
        }
    }

    private void checkAndAddValues(Object copyFrom, Object copyTo, Field field, Copy copy, IgnoreCopy ignoreCopy, boolean ignoreCopyFromCopyTo) {
        if (ignoreCopy == null && ignoreCopyFromCopyTo == false) {
            if( copy != null) {
                addValueToField(copyFrom,copyTo, field, copy.copyTo());
            } else {
                addValueToField(copyFrom, copyTo, field, field.getName());
            }
        }
    }


    private boolean ignoreCopyToField(final Object copyTo, final String fieldName) {
        Field field = null;
        List<String> fieldNames = checkFieldExists(copyTo);
            IgnoreCopy ignoreCopy = null;
            try {
                if (fieldNames.contains(fieldName)) {
                    field = copyTo.getClass().getDeclaredField(fieldName);
                    ignoreCopy = field.getAnnotation(IgnoreCopy.class);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            return  ignoreCopy != null;
    }

    private List<String> checkFieldExists(Object copyTo) {
        List<String> fieldNames = new ArrayList<String>();
        Field[] fields = copyTo.getClass().getDeclaredFields();
        for (Field field1: fields) {
            fieldNames.add(field1.getName());
        }
        return fieldNames;
    }

    /**
     * this method copies the actual values.
     * @param copyFrom the bean that contains the values.
     * @param copyTo the bean where the values should be copied to.
     * @param field the field that contains the value to be copied.
     * @param name the field name to search for in the copyTo bean.
     */
    private void addValueToField(final Object copyFrom, final Object copyTo, final Field field, final String name) {
        try {
            List<String> fieldNames = checkFieldExists(copyTo);
            if(fieldNames.contains(name)) {
                Field copyToField = copyTo.getClass().getDeclaredField(name);
                if (copyToField != null) {
                    copyToField.setAccessible(true);
                    field.setAccessible(true);
                    copyToField.set(copyTo, field.get(copyFrom));
                    copyToField.setAccessible(false);
                    field.setAccessible(false);
                }
            }
        } catch (NoSuchFieldException e) {
            log.error("could not find field " + name);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            log.error("could not access field " + name);
            e.printStackTrace();
        }
    }
}
