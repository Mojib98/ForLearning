package example.optional;

import java.util.Optional;

// ===== سناریوی دوم: Employee / Department =====

public class Employee {
    private String name;
    private Optional<Department> department;

    public Employee(String name, Optional<Department> department) {
        this.name = name;
        this.department = department;
    }

    public String getName() { return name; }
    public Optional<Department> getDepartment() { return department; }
}

class Department {
    private String name;
    private Optional<Employee> manager; // ممکن است دپارتمان مدیر نداشته باشد

    public Department(String name, Optional<Employee> manager) {
        this.name = name;
        this.manager = manager;
    }

    public String getName() { return name; }
    public Optional<Employee> getManager() { return manager; }
}
