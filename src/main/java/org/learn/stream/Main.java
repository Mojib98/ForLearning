package org.learn.stream;

import org.learn.stream.dto.Employee;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors.*;
import java.util.stream.Stream;

import static java.lang.IO.println;
import static java.util.stream.Collectors.*;
import static org.learn.stream.dto.Employee.Department.*;
import static org.learn.stream.dto.Employee.Gender.FEMALE;
import static org.learn.stream.dto.Employee.Gender.MALE;

public class Main {
    static void main() {
        List<Employee> employees = List.of(
                new Employee(1L,"Ali",IT,MALE,28,7000,true),
                new Employee(2L,"Sara",HR,FEMALE,31,9000,true),
                new Employee(3L,"Reza",IT,MALE,35,12000,false),
                new Employee(4L,"Mina",FINANCE,FEMALE,27,8000,true),
                new Employee(5L,"Amir",SALES,MALE,24,5000,true),
                new Employee(6L,"Neda",IT,FEMALE,29,9500,true),
                new Employee(7L,"Hossein",SALES,MALE,41,15000,false),
                new Employee(8L,"Maryam",HR,FEMALE,38,11000,true)
        );

        /*Group all employees by department.*/
        Map<Employee.Department, List<Employee>> byDepartment = employees.stream().collect(groupingBy(Employee::getDepartment));
        printMap(byDepartment);

        /*Count the number of employees in each department.*/
        Map<Employee.Department, Long> countEmployee = employees.stream().collect(groupingBy(Employee::getDepartment, counting()));
        printMap(countEmployee);

        /*Find the employee with the highest salary in each department.*/
        Map<Employee.Department, Employee> maxSalary = employees.stream().collect(groupingBy(Employee::getDepartment, collectingAndThen(maxBy(Comparator.comparing(Employee::getSalary)), Optional::get)));
        printMap(maxSalary);

        /*Group employees by gender.*/
        Map<Employee.Gender, List<Employee>> employeeWithGender = employees.stream().collect(groupingBy(Employee::getGender));
        printMap(employeeWithGender);


        /*Group employees first by department and then by gender.*/
        Map<Employee.Department, Map<Employee.Gender, List<Employee>>> genderAndDepartment = employees.stream().collect(groupingBy(Employee::getDepartment, groupingBy(Employee::getGender)));
        printMap(genderAndDepartment);

        /*Calculate the total salary of each department.*/
        Map<Employee.Department, Integer> salaryDepartment= employees.stream().collect(groupingBy(Employee::getDepartment, summingInt(Employee::getSalary)));
        printMap(salaryDepartment);

        /*Create a Map where the key is the department and the value is a List of employee names.*/
        Map<Employee.Department, List<String>> employeeNameInDepartment = employees.stream().collect(groupingBy(Employee::getDepartment, mapping(Employee::getName, toList())));
        printMap(employeeNameInDepartment);

        /*Group employees by whether they are active or inactive.*/
        Map<String, List<Employee>> status = employees.stream().collect(groupingBy(employee -> {
            if (employee.isActive())
                return "ACTIVE";
            else
                return "NOT_ACTIVE";
        }));
        printMap(status);

        /*Find the average salary of employees in each department.*/
        Map<Employee.Department, Double> average = employees.stream().collect(groupingBy(Employee::getDepartment, averagingInt(Employee::getSalary)));
        printMap(average);

        /*Group employees by department and, inside each department, find the highest-paid employee.*/
        Map<Employee.Department, Employee> maxSalary1 = employees.stream().collect(groupingBy(Employee::getDepartment, collectingAndThen(maxBy(Comparator.comparing(Employee::getSalary)), Optional::get)));
        printMap(maxSalary1);

        Map<Boolean, List<Employee>> isActive = employees.stream().collect(partitioningBy(Employee::isActive));
        printMap(isActive);

        var isActiveByDepartment = employees.stream().collect(partitioningBy(Employee::isActive,
                groupingBy(Employee::getDepartment)));
        printMap(isActiveByDepartment);

        Map<Boolean, Employee> collect = employees.stream().collect(partitioningBy(Employee::isActive,
                collectingAndThen(maxBy(Comparator.comparing(Employee::getSalary)), Optional::get)));
        printMap(collect);
        /*ــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ
        * Collector*/

    }

    private static void printMap(Map map){
        System.out.println("____________________________________________");
        map.forEach((d,e )->{
            println(d + " -> " +e);
        });
        System.out.println("____________________________________________");
    }
}
