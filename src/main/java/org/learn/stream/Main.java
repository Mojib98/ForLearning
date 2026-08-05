package org.learn.stream;

import org.learn.stream.dto.Employee;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.IO.println;
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
        Map<Employee.Department, List<Employee>> byDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        byDepartment.forEach((d,e )->{
            println(d + " -> " +e);
        });
        /*Count the number of employees in each department.*/


    }

    private static void print(Stream stream){
        stream.forEach(s ->
        {
            System.out.println(s);
        });

    }
}
