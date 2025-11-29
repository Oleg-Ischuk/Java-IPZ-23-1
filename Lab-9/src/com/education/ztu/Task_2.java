package com.education.ztu;

import java.util.regex.*;

public class Task_2 {
    public static void main(String[] args) {
        String text =
                "ПІБ: Іваненко Іван Іванович, Дата народження: 20.05.1995, Посада: Менеджер, Досвід: 5 років, Адреса: Київ, Email: ivan.ivanenko@example.com, Телефон: +380(67)123-45-67\n" +
                        "ПІБ: Петров Петро Петрович, Дата народження: 11.12.1988, Посада: Програміст, Досвід: 10 років, Адреса: Харків, Email: petro.petrov@gmail.com, Телефон: +380931234568\n" +
                        "ПІБ: Коваленко Олена Сергіївна, Дата народження: 03.07.1992, Посада: Дизайнер, Досвід: 7 років, Адреса: Львів, Email: olena.design@ukr.net, Телефон: 067-222-33-44\n" +
                        "ПІБ: Мироненко Андрій Олегович, Дата народження: 15.01.1990, Посада: Бухгалтер, Досвід: 8 років, Адреса: Дніпро, Email: andrii.miron@gmail.com, Телефон: +38050-555-66-77\n" +
                        "ПІБ: Савченко Марія Василівна, Дата народження: 29.09.1998, Посада: HR, Досвід: 3 роки, Адреса: Одеса, Email: maria.hr@example.com, Телефон: 380631112233";

        Pattern phonePattern = Pattern.compile("\\+?\\d[\\d()\\-\\s]{8,16}\\d");
        Pattern emailPattern = Pattern.compile("[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}");
        Pattern datePattern = Pattern.compile("(\\d{2})\\.(\\d{2})\\.(\\d{4})");

        Matcher phones = phonePattern.matcher(text);
        Matcher emails = emailPattern.matcher(text);

        System.out.println("Телефони:");
        while (phones.find()) System.out.println(phones.group());

        System.out.println("\nЕмайли:");
        while (emails.find()) System.out.println(emails.group());

        String updatedDates = datePattern.matcher(text).replaceAll("$3-$2-$1");

        String updatedPositions = updatedDates
                .replace("Менеджер", "Керівник проекту")
                .replace("Дизайнер", "UI/UX спеціаліст")
                .replace("HR", "HR-менеджер");

        System.out.println("\nОновлений текст:");
        System.out.println(updatedPositions);
    }
}

