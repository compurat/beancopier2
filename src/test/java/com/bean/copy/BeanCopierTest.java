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
        testBean.setIgnore("ignore");
        beanBeanCopier = new BeanCopier();
        beanBeanCopier.beanCopy(testBean,emptyTestBean);

    }

    @Test
    public void testBeanCopyWithAnnotation() {
        Assert.assertNull(emptyTestBean.getTestString2());

    }

    @Test
    public void testBeanCopyWithNoAnnotation() {
        Assert.assertTrue(TEST_STRING_NO_ANNOTATION.equals(emptyTestBean.getTestStringNoAnnotation()));

    }

    @Test
    public void testBeanCopyWithIgnore() {
        beanBeanCopier.beanCopy(testBean,emptyTestBean);
        Assert.assertNull(emptyTestBean.getIgnore());

    }

}