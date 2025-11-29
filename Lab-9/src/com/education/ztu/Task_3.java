package com.education.ztu;

import java.lang.reflect.*;

class Employee {
    public String name;
    private int age;
    private String position;

    public Employee() {
        this.name = "Default";
        this.age = 20;
        this.position = "None";
    }

    public Employee(String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public void printInfo() {
        System.out.println(name + ", " + age + ", " + position);
    }

    private String secretCode() {
        return "SC-" + age;
    }
}

public class Task_3 {
    public static void main(String[] args) throws Exception {
        Class<?> c1 = Employee.class;
        Class<?> c2 = Class.forName("com.education.ztu.Employee");
        Class<?> c3 = new Employee().getClass();

        System.out.println("Поля:");
        Field[] fields = c1.getDeclaredFields();
        for (Field f : fields) System.out.println(f.getName() + " : " + f.getType().getSimpleName());

        System.out.println("\nМетоди:");
        Method[] methods = c1.getDeclaredMethods();
        for (Method m : methods) {
            System.out.print(m.getName() + "(");
            Class<?>[] params = m.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(") -> " + m.getReturnType().getSimpleName());
        }

        System.out.println("\nКонструктори:");
        Constructor<?>[] constructors = c1.getDeclaredConstructors();
        for (Constructor<?> ctor : constructors) {
            System.out.print(ctor.getName() + "(");
            Class<?>[] params = ctor.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        System.out.println("\nСтворення екземпляра:");
        Object obj = c1.getConstructor(String.class, int.class, String.class)
                .newInstance("Олег", 20, "Програміст");
        Method printInfo = c1.getMethod("printInfo");
        printInfo.invoke(obj);

        System.out.println("\nРобота з приватним полем age:");
        Field ageField = c1.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(obj, 30);
        System.out.println("Нове значення age: " + ageField.get(obj));
    }
}
