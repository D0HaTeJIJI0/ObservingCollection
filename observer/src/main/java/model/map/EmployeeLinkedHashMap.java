package model.map;

import entity.Department;
import entity.Employee;
import model.EmployeeCollection;
import model.comparator.ComparatorByDepartmentBySalary;

import java.math.BigDecimal;
import java.util.*;

public class EmployeeLinkedHashMap implements EmployeeCollection {

    private LinkedHashMap<Integer, Employee> employeeLinkedHashMap;

    private EmployeeLinkedHashMap() {}

    public EmployeeLinkedHashMap(LinkedHashMap<Integer, Employee> employeeLinkedHashMap) {
        this.employeeLinkedHashMap = new LinkedHashMap<>(employeeLinkedHashMap);
    }

    public LinkedHashMap<Integer, Employee> getEmployeeLinkedHashMap() {
        return employeeLinkedHashMap;
    }

    public void setEmployeeLinkedHashMap(LinkedHashMap<Integer, Employee> employeeLinkedHashMap) {
        this.employeeLinkedHashMap = new LinkedHashMap<>(employeeLinkedHashMap);
    }

    @Override
    public void deleteEmployeesWithMaxSalary() {
        List<Integer> maxSalaryIds = this.takeIdsMaxSalary();
        maxSalaryIds.forEach(
                this.employeeLinkedHashMap::remove
        );

    }

    @Override
    public void deleteEmployeesWithMinSalary() {
        List<Integer> minSalaryIds = this.takeIdsMinSalary();
        minSalaryIds.forEach(
                this.employeeLinkedHashMap::remove
        );
    }

    @Override
    public void deleteEmployeesOlderThan(int age) {
        List<Integer> idList = new ArrayList<>();
        this.employeeLinkedHashMap.forEach(
                (key, value) -> {
                    if (value.getAge() > age) {
                        idList.add(key);
                    }
                }
        );
        idList.forEach(
                this.employeeLinkedHashMap::remove
        );
    }

    @Override
    public BigDecimal takeSummarizeEmployeesSalary() {
        BigDecimal sum = new BigDecimal("0");
        for (Employee e: this.employeeLinkedHashMap.values()) {
            sum = sum.add(e.getSalary());
        }
        return sum;
    }

    @Override
    public Employee takeEmployeeByIndex(int i) {
        return this.employeeLinkedHashMap.get(i);
    }

    @Override
    public Collection<Employee> takeEmployees(int fromIndex, int amount) {
        return new ArrayList<>(this.employeeLinkedHashMap.values()).subList(fromIndex, amount);
    }

    @Override
    public Collection<Employee> takeEmployeesFromDepartment(Department department) {
        List<Employee> result = new ArrayList<>();
        for (Employee e: this.employeeLinkedHashMap.values()) {
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
        for (Employee e: this.employeeLinkedHashMap.values()) {
            if (!e.getName().toString().contains(str)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void sortByDepartmentBySalary() {
        List<Employee> employeeList = new ArrayList<>(this.employeeLinkedHashMap.values());
        employeeList.sort(new ComparatorByDepartmentBySalary());
        int id = 1;
        for (Employee e: employeeList) {
            this.employeeLinkedHashMap.put(id++, e);
        }
    }

    private List<Integer> takeIdsMaxSalary() {
        Iterator<Integer> it = this.employeeLinkedHashMap.keySet().iterator();
        BigDecimal maxSalary = null;
        BigDecimal curSalary;
        while (it.hasNext()) {
            curSalary = this.employeeLinkedHashMap.get(it.next()).getSalary();
            if (maxSalary == null || maxSalary.compareTo(curSalary) > 0) {
                maxSalary = curSalary;
            }
        }
        List<Integer> listId = new ArrayList<>();
        for (Integer key: this.employeeLinkedHashMap.keySet()) {
            if (maxSalary != null && this.employeeLinkedHashMap.get(key).getSalary().compareTo(maxSalary) == 0) {
                listId.add(key);
            }
        }
        return listId;
    }

    private List<Integer> takeIdsMinSalary() {
        Iterator<Integer> it = this.employeeLinkedHashMap.keySet().iterator();
        BigDecimal minSalary = null;
        BigDecimal curSalary;
        while (it.hasNext()) {
            curSalary = this.employeeLinkedHashMap.get(it.next()).getSalary();
            if (minSalary == null || minSalary.compareTo(curSalary) < 0) {
                minSalary = curSalary;
            }
        }
        List<Integer> listId = new ArrayList<>();
        for (Integer key: this.employeeLinkedHashMap.keySet()) {
            if (minSalary != null && this.employeeLinkedHashMap.get(key).getSalary().compareTo(minSalary) == 0) {
                listId.add(key);
            }
        }
        return listId;
    }



}
