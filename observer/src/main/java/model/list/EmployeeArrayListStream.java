package model.list;

import entity.Department;
import entity.Employee;
import model.EmployeeCollection;
import model.exception.EmptyCollectionException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeArrayListStream implements EmployeeCollection {

    private List<Employee> employeeList;

    private EmployeeArrayListStream() {}

    public EmployeeArrayListStream(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public void deleteEmployeesWithMaxSalary() throws EmptyCollectionException {
        BigDecimal maxSalary = this.employeeList.stream()
                .map(Employee::getSalary)
                .max(BigDecimal::compareTo)
                .orElseThrow(EmptyCollectionException::new);

        this.employeeList = this.employeeList.stream()
                .filter(e -> !e.getSalary().equals(maxSalary))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeesWithMinSalary() throws EmptyCollectionException {
        BigDecimal maxSalary = this.employeeList.stream()
                .map(Employee::getSalary)
                .min(BigDecimal::compareTo)
                .orElseThrow(EmptyCollectionException::new);

        this.employeeList = this.employeeList.stream()
                .filter(e -> !e.getSalary().equals(maxSalary))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeesOlderThan(int age) {
        this.employeeList = this.employeeList.stream()
                .filter(e -> e.getAge() <= age)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal takeSummarizeEmployeesSalary() {
        return this.employeeList.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Employee takeEmployeeByIndex(int i) {
        return this.employeeList.get(i);
    }

    @Override
    public Collection<Employee> takeEmployees(int fromIndex, int amount) {
        return this.employeeList.subList(fromIndex, amount);
    }

    @Override
    public Collection<Employee> takeEmployeesFromDepartment(Department department) {
        return this.employeeList.stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEmployeesHasCharacterInName(char c) {
        char []characters = new char[1];
        characters[0] = c;
        String str = new String(characters);
        return this.employeeList.stream()
                .allMatch(employee -> employee.getName().toString().contains(str));
    }

    @Override
    public void sortByDepartmentBySalary() {
        this.employeeList = this.employeeList.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    // добавить "_1" к имени каждого элементу первой коллекции
    public void addToAllEmployeesName(int offset, String s) {
        this.employeeList.stream()
                .forEach(employee -> employee.addToName(offset, s));
    }
}
