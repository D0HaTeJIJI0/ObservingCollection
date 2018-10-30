package entity;

import java.math.BigDecimal;

public class Employee implements Comparable<Employee>{
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

    @Override
    public int compareTo(Employee o) {
        if (this.getDepartment() == o.getDepartment()) {
            return this.getSalary().compareTo(o.getSalary());
        }
        return this.getDepartment().compareTo(o.getDepartment());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name=" + name +
                ", department=" + department +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
