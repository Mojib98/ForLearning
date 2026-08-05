package org.learn.stream.dto;

public class Employee {

    public enum Department {
        IT,
        HR,
        FINANCE,
        SALES
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    private Long id;
    private String name;
    private Department department;
    private Gender gender;
    private int age;
    private int salary;
    private boolean active;

    public Employee(Long id,
                    String name,
                    Department department,
                    Gender gender,
                    int age,
                    int salary,
                    boolean active) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return name;
    }
}