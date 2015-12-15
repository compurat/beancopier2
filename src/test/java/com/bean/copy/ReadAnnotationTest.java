package com.bean.copy;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by pnlpr114 on 14-12-2015.
 */
public class ReadAnnotationTest {
    Set<Entry<String, String>> mapperSet = null;
    Map<String,String> fieldToFieldMapper = null;
    @Before
    public void init() {
        ReadAnnotation readAnnotation = new ReadAnnotation();
        fieldToFieldMapper = readAnnotation.readValue(new TestBean());
        mapperSet = fieldToFieldMapper.entrySet();

    }

    @Test
    public void testReadFieldNameValue() {
        Assert.assertEquals("testString", mapperSet.iterator().next().getKey() );
    }

    @Test
    public void testReadAnnotationNameValue() {
        Assert.assertEquals("testString2", mapperSet.iterator().next().getValue());
    }

    @Test
    public void testReadAnnotationNameValueNoAnnotation() {
        Assert.assertEquals("testStringNoAnnotation", fieldToFieldMapper.get("testStringNoAnnotation"));
    }
}