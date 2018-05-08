package com.liubin.fenghui.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/12/7.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface FieldTypeAnnotation {
    String type() default "ignore";
    int age() default 27;
    String[] hobby(); //没有指定defalut的，需要在注解的时候显式指明
}
