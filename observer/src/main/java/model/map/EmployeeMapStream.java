package model.map;

import entity.Department;
import entity.Employee;
import model.EmployeeCollection;
import model.exception.EmptyCollectionException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeMapStream implements EmployeeCollection {

    private Map<Integer, Employee> employeeMap;

    private EmployeeMapStream() {}

    public EmployeeMapStream(Map<Integer, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    @Override
    public void deleteEmployeesWithMaxSalary() throws EmptyCollectionException {
        BigDecimal maxSalary = this.employeeMap.values()
                .stream()
                .map(Employee::getSalary)
                .max(BigDecimal::compareTo)
                .orElseThrow(EmptyCollectionException::new);

        this.employeeMap = this.employeeMap.entrySet()
                .stream()
                .filter(employee -> !employee.getValue().getSalary().equals(maxSalary))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteEmployeesWithMinSalary() throws EmptyCollectionException {
        BigDecimal maxSalary = this.employeeMap.values()
                .stream()
                .map(Employee::getSalary)
                .min(BigDecimal::compareTo)
                .orElseThrow(EmptyCollectionException::new);

        this.employeeMap = this.employeeMap.entrySet()
                .stream()
                .filter(employee -> !employee.getValue().getSalary().equals(maxSalary))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteEmployeesOlderThan(int age) {
        this.employeeMap = this.employeeMap.entrySet()
                .stream()
                .filter(employee -> employee.getValue().getAge() <= age)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public BigDecimal takeSummarizeEmployeesSalary() {
        return this.employeeMap.values()
                .stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Employee takeEmployeeByIndex(int i) {
        return this.employeeMap.values()
                .stream()
                .collect(Collectors.toList())
                .get(i);
    }

    @Override
    public Collection<Employee> takeEmployees(int fromIndex, int amount) {
        return this.employeeMap.values()
                .stream()
                .collect(Collectors.toList())
                .subList(fromIndex, amount);
    }

    @Override
    public Collection<Employee> takeEmployeesFromDepartment(Department department) {
        return this.employeeMap.values()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEmployeesHasCharacterInName(char c) {
        char []characters = new char[1];
        characters[0] = c;
        String str = new String(characters);
        return this.employeeMap.values()
                .stream()
                .allMatch(employee -> employee.getName().toString().contains(str));
    }

    @Override
    public void sortByDepartmentBySalary() {
        this.employeeMap = this.employeeMap.entrySet()
                .stream()
                .sorted()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
