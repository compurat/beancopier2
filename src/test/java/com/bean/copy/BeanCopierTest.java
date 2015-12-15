package com.bean.copy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by compurat on 12/14/15.
 */
public class BeanCopierTest {

    public static final String TEST_STRING_WITH_ANNOTATION = "1";
    public static final String TEST_STRING_NO_ANNOTATION = "2";
    private BeanCopier beanBeanCopier;
    private TestBean testBean = new TestBean();
    private EmptyTestBean emptyTestBean = new EmptyTestBean();

    @Before
    public void init() {
        testBean.setTestString(TEST_STRING_WITH_ANNOTATION);
        testBean.setTestStringNoAnnotation(TEST_STRING_NO_ANNOTATION);
        beanBeanCopier = new BeanCopier();

    }

    @Test
    public void testBeanCopyWithAnnotation() {
        beanBeanCopier.beanCopy(testBean,emptyTestBean);
        Assert.assertTrue(TEST_STRING_WITH_ANNOTATION.equals(emptyTestBean.getTestString2()));

    }

    @Test
    public void testBeanCopyWithNoAnnotation() {
        beanBeanCopier.beanCopy(testBean,emptyTestBean);
        Assert.assertTrue(TEST_STRING_NO_ANNOTATION.equals(emptyTestBean.getTestStringNoAnnotation()));

    }

}