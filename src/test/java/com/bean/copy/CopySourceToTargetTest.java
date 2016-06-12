package com.bean.copy;

import org.junit.Test;

/**
 * Created by compurat on 2/3/16.
 */
public class CopySourceToTargetTest {

    @Test
    public void copyTest() {
        CopySourceToTarget copySourceToTarget = new CopySourceToTarget();
        copySourceToTarget.copy(new TestBean());

    }

}