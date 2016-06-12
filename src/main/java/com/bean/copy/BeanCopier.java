package com.bean.copy;

import com.bean.copy.annotation.Copy;
import com.bean.copy.annotation.IgnoreCopy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * this class converts one bean to another.
 */

public class BeanCopier {
private static final Logger LOGGER = LoggerFactory.getLogger(BeanCopier.class);

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
            String fieldName;
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

    /**
     * checks if ignorefield is set in the target.class
     * @param copyTo the target class.
     * @param fieldName the fieldname to be set.
     * @return a boolean if the field is right to set.
     */
    private boolean ignoreCopyToField(final Object copyTo, final String fieldName) {
        Field field;
        List<String> fieldNames = checkFieldExists(copyTo);
            IgnoreCopy ignoreCopy = null;
            try {
                if (fieldNames.contains(fieldName)) {
                    field = copyTo.getClass().getDeclaredField(fieldName);
                    ignoreCopy = field.getAnnotation(IgnoreCopy.class);
                }
            } catch (NoSuchFieldException e) {
                LOGGER.error("could not find field " + fieldName,e);
            }

            return  ignoreCopy != null;
    }

    /**
     * creates a list that has all the fieldnames in it.
     * @param copyTo the target class
     * @return a list that has all the fieldnames in it
     */
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
            LOGGER.error("could not find field " + name,e);
            } catch (IllegalAccessException e) {
            LOGGER.error("could not access field " + name, e);
        }
    }
}
