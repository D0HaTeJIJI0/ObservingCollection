package model;

import entity.Department;
import entity.Employee;

import java.math.BigDecimal;
import java.util.Collection;

public interface EmployeeCollection {
    // удаления по максимальному, минимальному значению какого-нибудь поля
    void deleteEmployeesWithMaxSalary();
    void deleteEmployeesWithMinSalary();
    //  удаления из коллекции всех значений со значением поля меньше или больше X
    void deleteEmployeesOlderThan(int age);
    // посчитать суммарное значение поля
    BigDecimal takeSummarizeEmployeesSalary();
    //  вернуть третий элемент коллекции по порядку
    Employee takeEmployeeByIndex(int i);
    // вернуть два элемента начиная со второго
    Collection<Employee> takeEmployees(int fromIndex, int amount);
    // выбрать все элементы по шаблону
    Collection<Employee> takeEmployeesFromDepartment(Department department);
    // найти есть ли символ «{любой символ}» у всех элементов коллекции
    boolean isEmployeesHasCharacterInName(char c);
    // добавить "_1" к имени каждого элементу первой коллекции
    default void addToAllEmployeesName(int offset, String s) {}
    // отсортировать коллекцию элементов сначала по одному полю, а потом по второму
    void sortByDepartmentBySalary();
}
