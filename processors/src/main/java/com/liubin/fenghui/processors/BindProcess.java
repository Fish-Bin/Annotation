package com.liubin.fenghui.processors;

import com.google.auto.service.AutoService;
import com.liubin.fenghui.annotations.BindAnnotation;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;

/**
 * 处理HelloWorld注解.
 */
@AutoService(Processor.class)
public class BindProcess extends AbstractProcessor {

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        // Filer是个接口，支持通过注解处理器创建新文件
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(BindAnnotation.class)) {
            //对于Element直接强转
            ExecutableElement executableElement = (ExecutableElement) element;
            //非对应的Element，通过getEnclosingElement转换获取
            TypeElement classElement = (TypeElement) element.getEnclosingElement();
            //需要使用elementUtils来获取
            Elements elementUtils = processingEnv.getElementUtils();
            PackageElement packageElement = elementUtils.getPackageOf(classElement);
            //全类名
            String fullClassName = classElement.getQualifiedName().toString();//com.liubin.fenghui.annotation.MainActivity
            //类名
            String className = classElement.getSimpleName().toString();//MainActivity
            //包名
            String packageName = packageElement.getQualifiedName().toString();//com.liubin.fenghui.annotation
            //方法名
            String methodName = executableElement.getSimpleName().toString();//get

            System.out.println("fullClassName= " + fullClassName + "\nclassName= " + className + "\npackageName= " + packageName + "\nmethodName= " + methodName);

            ClassName c =ClassName.get(packageName,className);

            MethodSpec run = MethodSpec.methodBuilder("run")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(void.class)
                    .addStatement("new $T()."+methodName+"()", c)
                    .build();
            // 创建HelloWorld类
            TypeSpec binImpl = TypeSpec.classBuilder("BinImpl")
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(IBin.class)
                    .addMethod(run)
                    .build();

            try {
                JavaFile javaFile = JavaFile.builder("com.liubin.fenghui.annotation", binImpl).build();
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(BindAnnotation.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
