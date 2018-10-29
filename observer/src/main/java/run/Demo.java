package run;

import entity.Department;
import entity.Employee;
import model.list.EmployeeArrayList;
import model.map.EmployeeLinkedHashMap;
import view.Viewer;

import java.util.List;

public class Demo {
    private List<Employee> employeeList;
    private final String FILE_NAME = "input.txt";

    private Demo() {
        employeeList = readFile();
        Viewer.printList(employeeList);
        Viewer.printNoStream();
        workWithListNoStream();
        workWithMapNoStream();
        Viewer.printWithStream();
        workWithListWithStream();
        workWithMapWithStream();
    }

    private List<Employee> readFile() {
        return null;
    }

    private void workWithMapWithStream() {
    }

    private void workWithListWithStream() {

    }

    private void workWithMapNoStream() {
        EmployeeLinkedHashMap employeeLinkedHashMap = new EmployeeLinkedHashMap(this.employeeList);

        employeeLinkedHashMap.deleteEmployeesWithMinSalary();
        employeeLinkedHashMap.deleteEmployeesOlderThan(50);
        employeeLinkedHashMap.takeSummarizeEmployeesSalary();
        employeeLinkedHashMap.takeEmployeeByIndex(3);
        employeeLinkedHashMap.takeEmployees(2, 2);
        employeeLinkedHashMap.takeEmployeesFromDepartment(Department.TESTING);
        employeeLinkedHashMap.isEmployeesHasCharacterInName('c');
        employeeLinkedHashMap.addToAllEmployeesName(0,"_1");
        employeeLinkedHashMap.sortByDepartmentBySalary();
    }

    private void workWithListNoStream() {
        EmployeeArrayList employeeArrayList = new EmployeeArrayList(this.employeeList);

        employeeArrayList.deleteEmployeesWithMaxSalary();
        employeeArrayList.deleteEmployeesWithMinSalary();
        employeeArrayList.deleteEmployeesOlderThan(50);
        employeeArrayList.takeSummarizeEmployeesSalary();
        employeeArrayList.takeEmployeeByIndex(3);
        employeeArrayList.takeEmployees(2, 2);
        employeeArrayList.takeEmployeesFromDepartment(Department.TESTING);
        employeeArrayList.isEmployeesHasCharacterInName('c');
        employeeArrayList.addToAllEmployeesName(0, "_1");
        employeeArrayList.sortByDepartmentBySalary();
    }

    public static void main(String[] args) {
        new Demo();
    }
}
