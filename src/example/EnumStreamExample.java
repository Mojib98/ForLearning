package example;

import java.util.*;
import java.util.stream.*;

// Enum with fields and behavior
enum Department {
    ENGINEERING("Engineering", 1),
    SALES("Sales", 2),
    MARKETING("Marketing", 3),
    HR("Human Resources", 4);

    private final String displayName;
    private final int priority;

    Department(String displayName, int priority) {
        this.displayName = displayName;
        this.priority = priority;
    }

    public String getDisplayName() { return displayName; }
    public int getPriority() { return priority; }
}

enum Level {
    JUNIOR(1.0), MID(1.3), SENIOR(1.6), LEAD(2.0);

    private final double salaryMultiplier;

    Level(double salaryMultiplier) {
        this.salaryMultiplier = salaryMultiplier;
    }

    public double getSalaryMultiplier() { return salaryMultiplier; }
}

class Employee {
    private final String name;
    private final Department department;
    private final Level level;
    private final double baseSalary;

    public Employee(String name, Department department, Level level, double baseSalary) {
        this.name = name;
        this.department = department;
        this.level = level;
        this.baseSalary = baseSalary;
    }

    public String getName() { return name; }
    public Department getDepartment() { return department; }
    public Level getLevel() { return level; }
    public double getBaseSalary() { return baseSalary; }

    public double getActualSalary() {
        return baseSalary * level.getSalaryMultiplier();
    }

    @Override
    public String toString() {
        return String.format("%s [%s, %s, $%.2f]",
            name, department.getDisplayName(), level, getActualSalary());
    }
}

public class EnumStreamExample {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee("Alice", Department.ENGINEERING, Level.SENIOR, 90000),
            new Employee("Bob", Department.SALES, Level.MID, 60000),
            new Employee("Charlie", Department.ENGINEERING, Level.LEAD, 100000),
            new Employee("Diana", Department.MARKETING, Level.JUNIOR, 50000),
            new Employee("Eve", Department.ENGINEERING, Level.JUNIOR, 55000),
            new Employee("Frank", Department.SALES, Level.SENIOR, 75000),
            new Employee("Grace", Department.HR, Level.MID, 58000)
        );

        // 1. Group employees by Department (using EnumMap under the hood via groupingBy)
        Map<Department, List<Employee>> byDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                () -> new EnumMap<>(Department.class),
                Collectors.toList()
            ));
        byDept.forEach((dept, list) ->
            System.out.println(dept.getDisplayName() + ": " + list));

        System.out.println();

        // 2. Average actual salary per Department
        Map<Department, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                () -> new EnumMap<>(Department.class),
                Collectors.averagingDouble(Employee::getActualSalary)
            ));
        System.out.println("Average salary by department: " + avgSalaryByDept);

        // 3. Count employees per Level
        Map<Level, Long> countByLevel = employees.stream()
            .collect(Collectors.groupingBy(Employee::getLevel, Collectors.counting()));
        System.out.println("Count by level: " + countByLevel);

        // 4. Group by Department, then by Level (nested grouping)
        Map<Department, Map<Level, List<Employee>>> nested = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.groupingBy(Employee::getLevel)
            ));
        System.out.println("\nNested grouping: " + nested);

        // 5. Sort departments by their custom priority field, list top earner in each
        Map<Department, Optional<Employee>> topEarnerByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getActualSalary))
            ));
        System.out.println("\nTop earners:");
        topEarnerByDept.entrySet().stream()
            .sorted(Comparator.comparingInt(e -> e.getKey().getPriority()))
            .forEach(e -> System.out.println(e.getKey().getDisplayName() + " -> " + e.getValue().orElse(null)));

        // 6. Total payroll cost using reduce
        double totalPayroll = employees.stream()
            .map(Employee::getActualSalary)
            .reduce(0.0, Double::sum);
        System.out.printf("%nTotal payroll: $%.2f%n", totalPayroll);

        // 7. Partition employees into high earners (>70k) vs others
        Map<Boolean, List<Employee>> partitioned = employees.stream()
            .collect(Collectors.partitioningBy(e -> e.getActualSalary() > 70000));
        System.out.println("\nHigh earners: " + partitioned.get(true));
        System.out.println("Others: " + partitioned.get(false));

        // 8. Join all names in Engineering, sorted, comma-separated
        String engineeringNames = employees.stream()
            .filter(e -> e.getDepartment() == Department.ENGINEERING)
            .map(Employee::getName)
            .sorted()
            .collect(Collectors.joining(", "));
        System.out.println("\nEngineering team: " + engineeringNames);

        /*Remove all employees whose salary is less than 70000. Use removeIf().*/
//        employees.removeIf(e -> e.getBaseSalary() > 70000);
//        System.out.println("\nEngineering team: " + engineeringNames);
        /*Convert all employee names to uppercase. Use replaceAll().*/
//        employees.replaceAll(e ->e.getName().toUpperCase(Locale.ROOT));
        employees.sort(Comparator.comparing(Employee::getBaseSalary).reversed());
        Map<String, Integer> salaries = Map.of(
                "Alice", 90000,
                "Bob", 60000,
                "Charlie", 100000,
                "Diana", 50000,
                "Eve", 55000,
                "Frank", 75000,
                "Grace", 58000
        );
        List<Employee> employeesMap = List.of(
                new Employee("Alice", Department.ENGINEERING, Level.SENIOR, 90000),
                new Employee("Bob", Department.SALES, Level.MID, 60000),
                new Employee("Charlie", Department.ENGINEERING, Level.LEAD, 100000),
                new Employee("Diana", Department.MARKETING, Level.JUNIOR, 50000),
                new Employee("Eve", Department.ENGINEERING, Level.JUNIOR, 55000),
                new Employee("Frank", Department.SALES, Level.SENIOR, 75000),
                new Employee("Grace", Department.HR, Level.MID, 58000)
        );
        Map<Department,List<String>> map = new HashMap<>();
        employeesMap.forEach(employee -> {
            map.computeIfAbsent(employee.getDepartment(),n ->new ArrayList<>()).add(employee.getName());
        });

        List<Transaction> transactions = List.of(
                new Transaction("1001", "DEPOSIT", 5000),
                new Transaction("1002", "DEPOSIT", 7000),
                new Transaction("1001", "WITHDRAW", 2000),
                new Transaction("1001", "DEPOSIT", 3000),
                new Transaction("1002", "WITHDRAW", 1000),
                new Transaction("1002", "DEPOSIT", 4000)
        );
        Map<String, Long> result = new HashMap<>();
        transactions.forEach(s ->{
            result.merge(s.accountNumber(),s.amount(),Long::sum);
        });
        Map<String, Integer> resultString = new HashMap<>();

        List<String> words = List.of(
                "java",
                "java",
                "spring",
                "java",
                "stream",
                "spring"
        );
        words.forEach(s ->{
            resultString.merge(s,1, Integer::sum);
        });

        Map<String, Long> result3 = new HashMap<>();
        transactions.forEach(s ->{
            result.merge(s.accountNumber(),s.amount(),(old,n) ->{
                if (s.type().equals("DEPOSIT")){
                    return old+n;
                }else {
                    return old-n;
                }
                    }
                    );
        });
    }
}
