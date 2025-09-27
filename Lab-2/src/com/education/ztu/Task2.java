package com.education.ztu;

// ===================== ENUMS =====================

// Перелічуваний тип для локацій
enum LocationTask2 {
    KYIV, LVIV, ODESSA,
}

// Перелічуваний тип для статі
enum Gender {
    MALE, FEMALE, OTHER
}

// ===================== ІНТЕРФЕЙС =====================
interface Human {
    void sayFullName();
    void sayAge();
    void sayLocation();
    void sayGender();

    // метод за замовчуванням
    default void whoIAm() {
        System.out.println("I am a human.");
    }
}

// ===================== АБСТРАКТНИЙ КЛАС =====================
abstract class Person implements Human {
    private String firstName;
    private String lastName;
    private int age;
    protected LocationTask2 location;
    protected Gender gender;

    // статичний лічильник екземплярів
    private static int counter = 0;

    // Блок ініціалізації
    {
        firstName = "Unknown";
        lastName = "Unknown";
        age = 18;
        location = LocationTask2.KYIV;
        gender = Gender.OTHER;
        counter++;
    }

    // Конструктор без аргументів
    public Person() {}

    // Конструктор з аргументами
    public Person(String firstName, String lastName, int age, LocationTask2 location, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.location = location;
        this.gender = gender;
        counter++;
    }

    // Геттери та сеттери
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public LocationTask2 getLocation() { return location; }
    public void setLocation(LocationTask2 location) { this.location = location; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    // Методи інтерфейсу
    @Override
    public void sayFullName() {
        System.out.println("Full name: " + firstName + " " + lastName);
    }

    @Override
    public void sayAge() {
        System.out.println("Age: " + age);
    }

    @Override
    public void sayLocation() {
        System.out.println("Location: " + location);
    }

    @Override
    public void sayGender() {
        System.out.println("Gender: " + gender);
    }

    // Статичний метод для показу лічильника
    public static void showCounter() {
        System.out.println("Created persons: " + counter);
    }

    // Абстрактний метод
    public abstract String getOccupation();

    // Звичайний метод
    public void getFullInfo() {
        sayFullName();
        sayAge();
        sayLocation();
        sayGender();
        System.out.println("Occupation: " + getOccupation());
    }
}

// ===================== КЛАС STUDENT =====================
class Student extends Person {
    private String university;
    private String specialty;

    public Student() {
        super();
        this.university = "Unknown University";
        this.specialty = "Unknown Specialty";
    }

    public Student(String firstName, String lastName, int age, LocationTask2 location, Gender gender,
                   String university, String specialty) {
        super(firstName, lastName, age, location, gender);
        this.university = university;
        this.specialty = specialty;
    }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    @Override
    public String getOccupation() {
        return "Student";
    }

    public void study() {
        System.out.println(getFirstName() + " is studying " + specialty + " at " + university);
    }
}

// ===================== КЛАС CAR =====================
class Car {
    private String brand;
    private String model;

    private Engine engine;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.engine = new Engine();
    }

    public void startCar() {
        engine.startEngine();
    }

    public void stopCar() {
        engine.stopEngine();
    }

    // Внутрішній клас Engine
    class Engine {
        private boolean works;

        public void startEngine() {
            works = true;
            System.out.println("Engine started.");
        }

        public void stopEngine() {
            works = false;
            System.out.println("Engine stopped.");
        }

        public boolean isEngineWorks() {
            return works;
        }
    }

    public void showCarInfo() {
        System.out.println("Car: " + brand + " " + model);
    }
}

// ===================== КЛАС TEACHER =====================
class Teacher extends Person {
    private String subject;
    private Car car;

    public Teacher() {
        super();
        this.subject = "Unknown Subject";
        this.car = new Car("NoBrand", "NoModel");
    }

    public Teacher(String firstName, String lastName, int age, LocationTask2 location, Gender gender,
                   String subject, Car car) {
        super(firstName, lastName, age, location, gender);
        this.subject = subject;
        this.car = car;
    }

    @Override
    public String getOccupation() {
        return "Teacher";
    }

    public void teach() {
        System.out.println(getFirstName() + " is teaching " + subject);
    }

    public void driveCar() {
        car.showCarInfo();
        car.startCar();
    }
}

// ===================== КЛАС EMPLOYEE =====================
class Employee extends Person {
    private String company;
    private String position;
    private Car car;

    public Employee() {
        super();
        this.company = "Unknown Company";
        this.position = "Unknown Position";
        this.car = new Car("NoBrand", "NoModel");
    }

    public Employee(String firstName, String lastName, int age, LocationTask2 location, Gender gender,
                    String company, String position, Car car) {
        super(firstName, lastName, age, location, gender);
        this.company = company;
        this.position = position;
        this.car = car;
    }

    @Override
    public String getOccupation() {
        return "Employee";
    }

    public void work() {
        System.out.println(getFirstName() + " works as " + position + " at " + company);
    }

    public void driveCar() {
        car.showCarInfo();
        car.startCar();
    }
}

// ===================== MAIN =====================
public class Task2 {
    public static void main(String[] args) {
        // Створюємо студентів, викладачів та працівників
        Student st1 = new Student("Ivan", "Petrenko", 20, LocationTask2.KYIV, Gender.MALE,
                "KPI", "Computer Science");
        Teacher t1 = new Teacher("Olena", "Ivanova", 40, LocationTask2.LVIV, Gender.FEMALE,
                "Mathematics", new Car("Toyota", "Corolla"));
        Employee e1 = new Employee("Andriy", "Shevchenko", 30, LocationTask2.ODESSA, Gender.MALE,
                "SoftServe", "Developer", new Car("Tesla", "Model 3"));

        // Демонструємо методи
        st1.getFullInfo();
        st1.study();

        System.out.println("--------------------");

        t1.getFullInfo();
        t1.teach();
        t1.driveCar();

        System.out.println("--------------------");

        e1.getFullInfo();
        e1.work();
        e1.driveCar();

        System.out.println("--------------------");

        // Демонстрація статичного лічильника
        Person.showCounter();

        // Демонстрація instanceof
        if (st1 instanceof Person) {
            System.out.println("st1 is a Person");
        }
        if (t1 instanceof Teacher) {
            System.out.println("t1 is a Teacher");
        }
        if (e1 instanceof Human) {
            System.out.println("e1 implements Human");
        }
    }
}
