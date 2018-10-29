package entity;

import java.math.BigDecimal;

public class Employee {
    private StringBuffer name;
    private Department department;
    private BigDecimal salary;
    private int age;

    public StringBuffer getName() {
        return name;
    }

    public void setName(StringBuffer name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addToName(int offset, String s) {
        name.insert(offset, s);
    }
}
