package com.bean.copy;

import java.io.File;

/**
 * Created by compurat on 1/30/16.
 */
public class CopyTargetWithAnnotation {

    public void copy() {
        String[] classpathContent = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
        File[] classes = null;

        for(String className: classpathContent) {
            System.out.println(className);
            if(className.endsWith("classes") && !(className.endsWith("test-classes"))) {
                findClassFiles(new File(className).listFiles());
                break;
            }
        }
    }

    private File[] findClassFiles(File[] classes) {
        File[] classFiles = null;


        return classFiles;
    }
}
