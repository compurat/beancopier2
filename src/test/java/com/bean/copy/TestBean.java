package com.bean.copy;

import com.bean.copy.annotation.Copy;
import com.bean.copy.annotation.IgnoreCopy;

/**
 * Created by pnlpr114 on 14-12-2015.
 */
public class TestBean {

    @Copy(copyTo = "testString2")
    private String testString;

    private String testStringNoAnnotation;

    private String notInSourceBean;

    @IgnoreCopy
    private String ignore;

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

    public String getIgnore() {
        return ignore;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }

    public String getNotInSourceBean() {
        return notInSourceBean;
    }

    public void setNotInSourceBean(String notInSourceBean) {
        this.notInSourceBean = notInSourceBean;
    }
}
