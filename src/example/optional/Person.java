package example.optional;

import java.util.Optional;

// ===== سناریوی اول: Person / Car / Insurance =====

public class Person {
    private String name;
    private int age;
    private Optional<Car> car;

    public Person(String name, int age, Optional<Car> car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public Optional<Car> getCar() { return car; }
}

class Car {
    private String model;
    private Optional<Insurance> insurance;

    public Car(String model, Optional<Insurance> insurance) {
        this.model = model;
        this.insurance = insurance;
    }

    public String getModel() { return model; }
    public Optional<Insurance> getInsurance() { return insurance; }
}

class Insurance {
    private String name;       // شرکت بیمه همیشه اسم دارد
    private double monthlyFee;

    public Insurance(String name, double monthlyFee) {
        this.name = name;
        this.monthlyFee = monthlyFee;
    }

    public String getName() { return name; }
    public double getMonthlyFee() { return monthlyFee; }
}
