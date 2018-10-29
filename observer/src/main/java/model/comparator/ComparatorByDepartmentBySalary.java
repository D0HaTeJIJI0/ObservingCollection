package model.comparator;

import entity.Employee;

import java.util.Comparator;

public class ComparatorByDepartmentBySalary implements Comparator<Employee> {

    @Override
    public int compare(Employee e1, Employee e2) {
        if (e1.getDepartment() == e2.getDepartment()) {
            return e1.getSalary().compareTo(e2.getSalary());
        }
        return e1.getDepartment().compareTo(e2.getDepartment());
    }
}
