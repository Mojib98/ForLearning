package org.learn.stream.dto;

import java.util.List;

/**
 * Author: M.R.Khabire
 * Date: 06/08/2026
 * Time: 11:12
 */
public class DepartmentReport {
    private Employee.Department departmentReport;
    private Long employeeCount;
    private Double averageSalary;
    private Employee highestPaid;
    private List<String> activeEmployees;

    public Employee.Department getDepartmentReport() {
        return departmentReport;
    }

    public void setDepartmentReport(Employee.Department departmentReport) {
        this.departmentReport = departmentReport;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public void setHighestPaid(Employee highestPaid) {
        this.highestPaid = highestPaid;
    }

    public void setActiveEmployees(List<String> activeEmployees) {
        this.activeEmployees = activeEmployees;
    }



    public Long getEmployeeCount() {
        return employeeCount;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public Employee getHighestPaid() {
        return highestPaid;
    }

    public List<String> getActiveEmployees() {
        return activeEmployees;
    }
}
