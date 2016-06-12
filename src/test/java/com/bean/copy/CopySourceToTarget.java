package com.bean.copy;

import javassist.*;

import java.io.IOException;

/**
 * Created by compurat on 2/3/16.
 */
public class CopySourceToTarget {
    public void copy(final Object source) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(source.getClass().getName());
            cc.setName(source.getClass().getName() + "Target");
            cc.writeFile("target/classes");
            cc.stopPruning(true);
            cc.detach();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
