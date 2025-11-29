package com.education.ztu;

import java.lang.annotation.*;
import java.lang.reflect.*;

enum Level {
    LOW, MEDIUM, HIGH
}

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Infos.class)
@interface Info {
    String author() default "Unknown";
    String date() default "2025-01-01";
    String description() default "No description";
    int version() default 1;
    Level level() default Level.MEDIUM;
    String[] reviewers() default {};
}

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Infos {
    Info[] value();
}

@Info(author = "Oleg", date = "2025-12-01", description = "Person class", version = 2, level = Level.HIGH, reviewers = {"Anna", "Ivan"})
@Info(author = "Maria", description = "Second class annotation")
class Person {

    @Info(description = "Name field", level = Level.LOW)
    public String name;

    @Info(description = "Print method", version = 3, reviewers = {"Oleg"})
    public void print() {}
}

public class Task_4 {
    public static void main(String[] args) throws Exception {
        Class<Person> cls = Person.class;

        Info[] classInfos = cls.getAnnotationsByType(Info.class);
        for (Info i : classInfos) {
            System.out.println("CLASS: " + i.author() + ", " + i.date() + ", " + i.description() + ", version: " + i.version() + ", level: " + i.level());
            System.out.println("Reviewers: " + String.join(" | ", i.reviewers()));
            System.out.println();
        }
        Field field = cls.getDeclaredField("name");
        Info fieldInfo = field.getAnnotation(Info.class);
        System.out.println("FIELD: " + fieldInfo.description() + ", level: " + fieldInfo.level());

        Method method = cls.getDeclaredMethod("print");
        Info methodInfo = method.getAnnotation(Info.class);
        System.out.println("METHOD: " + methodInfo.description() + ", version: " + methodInfo.version());
        System.out.println("Reviewers: " + String.join(" | ", methodInfo.reviewers()));
    }
}
