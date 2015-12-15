package com.bean.copy;

import com.bean.copy.annotation.Copy;

/**
 * Created by pnlpr114 on 14-12-2015.
 */
public class TestBean {

    @Copy(copyTo = "testString2")
    private String testString;

    private String testStringNoAnnotation;

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public String getTestStringNoAnnotation() {
        return testStringNoAnnotation;
    }

    public void setTestStringNoAnnotation(String testStringNoAnnotation) {
        this.testStringNoAnnotation = testStringNoAnnotation;
    }
}
