package model.list;

import entity.Department;
import entity.Employee;
import model.EmployeeCollection;
import model.comparator.ComparatorByDepartmentBySalary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class EmployeeArrayList extends ArrayList<Employee> implements EmployeeCollection {

    public EmployeeArrayList(List<Employee> employeeList) {
        super(employeeList);
    }

    @Override
    public void deleteEmployeesWithMaxSalary() {
        BigDecimal maxSalary = this.takeMaxSalary();
        this.removeIf(
                e -> e.getSalary().compareTo(maxSalary) == 0
        );
    }

    @Override
    public void deleteEmployeesWithMinSalary() {
        BigDecimal maxSalary = this.takeMinSalary();
        this.removeIf(
                e -> e.getSalary().compareTo(maxSalary) == 0
        );
    }

    @Override
    public void deleteEmployeesOlderThan(int age) {
        this.removeIf(
                e -> e.getAge() > age
        );
    }

    @Override
    public BigDecimal takeSummarizeEmployeesSalary() {
        BigDecimal sum = new BigDecimal("0");
        for (Employee employee: this) {
            sum = sum.add(employee.getSalary());
        }
        return sum;
    }

    @Override
    public Employee takeEmployeeByIndex(int i) {
        return this.get(i);
    }

    @Override
    public List<Employee> takeEmployees(int fromIndex, int amount) {
        return this.subList(fromIndex, fromIndex + amount);
    }

    @Override
    public List<Employee> takeEmployeesFromDepartment(Department department) {
        List<Employee> result = new ArrayList<>();
       this.forEach(e -> {
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
        for (Employee employee: this) {
            if (!employee.getName().toString().contains(str)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void sortByDepartmentBySalary() {
        this.sort(new ComparatorByDepartmentBySalary());
    }

    // добавить "_1" к имени каждого элементу первой коллекции
    public void addToAllEmployeesName(int offset, String s) {
        this.forEach(e -> e.addToName(offset, s));
    }

    private BigDecimal takeMinSalary() {
        ListIterator<Employee> it = this.listIterator();
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
        ListIterator<Employee> it = this.listIterator();
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

