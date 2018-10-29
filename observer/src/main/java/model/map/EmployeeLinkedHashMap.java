package model.map;

import entity.Department;
import entity.Employee;
import model.EmployeeCollection;
import model.comparator.ComparatorByDepartmentBySalary;

import java.math.BigDecimal;
import java.util.*;

public class EmployeeLinkedHashMap extends LinkedHashMap<Integer, Employee> implements EmployeeCollection {

    public EmployeeLinkedHashMap(List<Employee> employeeList) {
        super(employeeList.size(), 0.75f, false);
        int id = 1;
        for (Employee e: employeeList) {
            this.put(id++, e);
        }
    }

    @Override
    public void deleteEmployeesWithMaxSalary() {
        List<Integer> maxSalaryIds = this.takeIdsMaxSalary();
        maxSalaryIds.forEach(
                this::remove
        );

    }

    @Override
    public void deleteEmployeesWithMinSalary() {
        List<Integer> minSalaryIds = this.takeIdsMinSalary();
        minSalaryIds.forEach(
                this::remove
        );
    }

    @Override
    public void deleteEmployeesOlderThan(int age) {
        List<Integer> idList = new ArrayList<>();
        this.forEach(
                (key, value) -> {
                    if (value.getAge() > age) {
                        idList.add(key);
                    }
                }
        );
        idList.forEach(
                this::remove
        );
    }

    @Override
    public BigDecimal takeSummarizeEmployeesSalary() {
        BigDecimal sum = new BigDecimal("0");
        for (Employee e: this.values()) {
            sum = sum.add(e.getSalary());
        }
        return sum;
    }

    @Override
    public Employee takeEmployeeByIndex(int i) {
        return this.get(i);
    }

    @Override
    public Collection<Employee> takeEmployees(int fromIndex, int amount) {
        return new ArrayList<>(this.values()).subList(fromIndex, amount);
    }

    @Override
    public Collection<Employee> takeEmployeesFromDepartment(Department department) {
        List<Employee> result = new ArrayList<>();
        for (Employee e: this.values()) {
            if (e.getDepartment() == department) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public boolean isEmployeesHasCharacterInName(char c) {
        char [] characters = new char[1];
        characters[0] = c;
        String str = new String(characters);
        for (Employee e: this.values()) {
            if (!e.getName().toString().contains(str)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void sortByDepartmentBySalary() {
        List<Employee> employeeList = new ArrayList<>(this.values());
        employeeList.sort(new ComparatorByDepartmentBySalary());
        int id = 1;
        for (Employee e: employeeList) {
            this.put(id++, e);
        }
    }

    private List<Integer> takeIdsMaxSalary() {
        Iterator<Integer> it = this.keySet().iterator();
        BigDecimal maxSalary = null;
        BigDecimal curSalary;
        while (it.hasNext()) {
            curSalary = this.get(it.next()).getSalary();
            if (maxSalary == null || maxSalary.compareTo(curSalary) > 0) {
                maxSalary = curSalary;
            }
        }
        List<Integer> listId = new ArrayList<>();
        for (Integer key: this.keySet()) {
            if (maxSalary != null && this.get(key).getSalary().compareTo(maxSalary) == 0) {
                listId.add(key);
            }
        }
        return listId;
    }

    private List<Integer> takeIdsMinSalary() {
        Iterator<Integer> it = this.keySet().iterator();
        BigDecimal minSalary = null;
        BigDecimal curSalary;
        while (it.hasNext()) {
            curSalary = this.get(it.next()).getSalary();
            if (minSalary == null || minSalary.compareTo(curSalary) < 0) {
                minSalary = curSalary;
            }
        }
        List<Integer> listId = new ArrayList<>();
        for (Integer key: this.keySet()) {
            if (minSalary != null && this.get(key).getSalary().compareTo(minSalary) == 0) {
                listId.add(key);
            }
        }
        return listId;
    }



}
