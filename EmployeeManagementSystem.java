import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A simple console application for managing employee details.
 * Compile with: javac EmployeeManagementSystem.java
 * Run with:     java EmployeeManagementSystem
 */
public class EmployeeManagementSystem {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Map<String, Employee> EMPLOYEES = new LinkedHashMap<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add employee");
            System.out.println("2. Display all employees");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            switch (INPUT.nextLine().trim()) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    displayEmployees();
                    break;
                case "3":
                    searchEmployee();
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Employee ID: ");
        String id = INPUT.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Employee ID cannot be empty.");
            return;
        }
        if (EMPLOYEES.containsKey(id)) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        System.out.print("Name: ");
        String name = INPUT.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Department: ");
        String department = INPUT.nextLine().trim();
        if (department.isEmpty()) {
            System.out.println("Department cannot be empty.");
            return;
        }

        System.out.print("Salary: ");
        String salaryText = INPUT.nextLine().trim();
        final double salary;
        try {
            salary = Double.parseDouble(salaryText);
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a valid numeric salary.");
            return;
        }
        if (!Double.isFinite(salary) || salary < 0) {
            System.out.println("Salary must be a non-negative number.");
            return;
        }

        EMPLOYEES.put(id, new Employee(id, name, department, salary));
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {
        if (EMPLOYEES.isEmpty()) {
            System.out.println("No employees have been added yet.");
            return;
        }

        System.out.println("\nEmployee details:");
        for (Employee employee : EMPLOYEES.values()) {
            printEmployee(employee);
        }
    }

    private static void searchEmployee() {
        System.out.print("Enter employee ID to search: ");
        String id = INPUT.nextLine().trim();
        Employee employee = EMPLOYEES.get(id);
        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            printEmployee(employee);
        }
    }

    private static void printEmployee(Employee employee) {
        System.out.println("------------------------------");
        System.out.println("ID:         " + employee.id);
        System.out.println("Name:       " + employee.name);
        System.out.println("Department: " + employee.department);
        System.out.printf("Salary:     %.2f%n", employee.salary);
    }

    private static final class Employee {
        private final String id;
        private final String name;
        private final String department;
        private final double salary;

        private Employee(String id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
    }
}
