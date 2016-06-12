package com.bean.copy;



import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Set;

@SupportedAnnotationTypes({"com.bean.copy.annotation.GenerateDto"})
public class DtoCreationProcessor  extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("/Home/compurat/TEST.txt","rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (TypeElement te : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(te)) {
                try {
                    randomAccessFile.write("Testing".getBytes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
