package com.bean.copy;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by compurat on 1/30/16.
 */
public class CopyTargetWithAnnotation {

    public void copy(final String classNameToFind) {
        String[] classpathContent = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
        File[] classes = null;

        for (String className : classpathContent) {
            System.out.println(className);
            if (className.endsWith("classes") && !(className.endsWith("test-classes"))) {
                findClassFiles(Arrays.asList(new File(className).listFiles()), classNameToFind);
                break;
            }
        }
    }

    private File findClassFiles(final List<File> classPath, final String classNameToFind) {
        for(int i = 0; i < classPath.size(); i++) {
            if(classPath.get(i).isDirectory()) {
                System.out.println("array");
            }
        }

        return null;
    }
}