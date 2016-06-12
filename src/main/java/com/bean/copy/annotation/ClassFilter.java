package com.bean.copy.annotation;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by compurat on 1/30/16.
 */
public class ClassFilter implements FileFilter {


    private String fileName ;
    public ClassFilter(final String fileName) {
        this.fileName = fileName;
    }

    public boolean accept(File pathname) {
        return pathname.getAbsolutePath().endsWith(fileName);    }
}
