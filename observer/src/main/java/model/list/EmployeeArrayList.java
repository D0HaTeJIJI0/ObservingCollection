package model.list;

import entity.Department;
import entity.Employee;
import model.EmployeeCollection;
import model.comparator.ComparatorByDepartmentBySalary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class EmployeeArrayList implements EmployeeCollection {

    private List<Employee> employeeList;

    private EmployeeArrayList() {}

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = new ArrayList<>(employeeList);
    }

    public EmployeeArrayList(List<Employee> employeeList) {
        this.employeeList = new ArrayList<>(employeeList);
    }

    @Override
    public void deleteEmployeesWithMaxSalary() {
        BigDecimal maxSalary = this.takeMaxSalary();
        this.employeeList.removeIf(
                e -> e.getSalary().compareTo(maxSalary) == 0
        );
    }

    @Override
    public void deleteEmployeesWithMinSalary() {
        BigDecimal maxSalary = this.takeMinSalary();
        this.employeeList.removeIf(
                e -> e.getSalary().compareTo(maxSalary) == 0
        );
    }

    @Override
    public void deleteEmployeesOlderThan(int age) {
        this.employeeList.removeIf(
                e -> e.getAge() > age
        );
    }

    @Override
    public BigDecimal takeSummarizeEmployeesSalary() {
        BigDecimal sum = new BigDecimal("0");
        for (Employee employee: this.employeeList) {
            sum = sum.add(employee.getSalary());
        }
        return sum;
    }

    @Override
    public Employee takeEmployeeByIndex(int i) {
        return this.employeeList.get(i);
    }

    @Override
    public List<Employee> takeEmployees(int fromIndex, int amount) {
        return this.employeeList.subList(fromIndex, fromIndex + amount);
    }

    @Override
    public List<Employee> takeEmployeesFromDepartment(Department department) {
        List<Employee> result = new ArrayList<>();
       this.employeeList.forEach(e -> {
           if (e.getDepartment() == department) {
               result.add(e);
           }
       });
        return result;
    }

    @Override
    public boolean isEmployeesHasCharacterInName(char c) {
        char [] characters = new char[1];
        characters[0] = c;
        String str = new String(characters);
        for (Employee employee: this.employeeList) {
            if (!employee.getName().toString().contains(str)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void sortByDepartmentBySalary() {
        this.employeeList.sort(new ComparatorByDepartmentBySalary());
    }

    // добавить "_1" к имени каждого элементу первой коллекции
    public void addToAllEmployeesName(int offset, String s) {
        this.employeeList.forEach(e -> e.addToName(offset, s));
    }

    private BigDecimal takeMinSalary() {
        ListIterator<Employee> it = this.employeeList.listIterator();
        BigDecimal minSalary = null;
        BigDecimal curSalary;
        while(it.hasNext()) {
            curSalary = it.next().getSalary();
            if (minSalary == null || minSalary.compareTo(curSalary) > 0) {
                minSalary = curSalary;
            }
        }
        return minSalary;
    }

    private BigDecimal takeMaxSalary() {
        ListIterator<Employee> it = this.employeeList.listIterator();
        BigDecimal maxSalary = null;
        BigDecimal curSalary;
        while(it.hasNext()) {
            curSalary = it.next().getSalary();
            if (maxSalary == null || maxSalary.compareTo(curSalary) < 0) {
                maxSalary = curSalary;
            }
        }
        return maxSalary;
    }
}

