package com.bean.copy;

import org.junit.Test;

/**
 * Created by compurat on 1/30/16.
 */
public class CopyTargetWithAnnotationTest {

    @Test
    public void testSearch() {
        CopyTargetWithAnnotation copyTargetWithAnnotation = new CopyTargetWithAnnotation();
        copyTargetWithAnnotation.copy();
    }
}