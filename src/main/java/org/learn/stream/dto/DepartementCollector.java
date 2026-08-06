package org.learn.stream.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Author: M.R.Khabire
 * Date: 06/08/2026
 * Time: 11:13
 */
public class DepartementCollector implements Collector<Employee, List<DepartmentReport>,List<DepartmentReport>> {


    @Override
    public Supplier<List<DepartmentReport>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<DepartmentReport>, Employee> accumulator() {
/*        return (List<DepartmentReport> d, Employee e) -> {
            Optional<DepartmentReport> any = d.stream().filter(d -> d.getDepartmentReport().equals(e.getDepartment())).findAny();
            if (any.isEmpty()) {
                DepartmentReport departmentReport = new DepartmentReport();
                departmentReport.setDepartmentReport(e.getDepartment());
                *//*departmentReport.getEmployeeCount().s
                d.add(departmentReport)
           *//* }
        };*/
        return null;
    }

    @Override
    public BinaryOperator<List<DepartmentReport>> combiner() {
        return null;
    }

    @Override
    public Function<List<DepartmentReport>, List<DepartmentReport>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
