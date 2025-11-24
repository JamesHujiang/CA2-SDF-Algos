/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author james
 */
public class CompanyManager {
    
    private ArrayList<Employee> staffList;      // Master list for sorting
    private ArrayList<Department> departments;  // Department list for grouping

    public CompanyManager() {
        this.staffList = new ArrayList<>();
        this.departments = new ArrayList<>();
        
        // Create Dummy Departments (To satisfy "3 types" requirement)
        departments.add(new Department("IT Development", "Floor 3"));
        departments.add(new Department("HR", "Floor 1"));
        departments.add(new Department("Sales", "Floor 2"));
        departments.add(new Department("Finance", "Floor 4"));
    }

    public void loadEmployeesFromFile(String filename) {
        staffList.clear(); 
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) scanner.nextLine(); // Skip Header

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length >= 9) {
                    // 1. Parse all 9 columns
                    String fn = data[0].trim();
                    String ln = data[1].trim();
                    String gender = data[2].trim();
                    String email = data[3].trim();
                    double salary = 0.0;
                    try { salary = Double.parseDouble(data[4].trim()); } catch(Exception e) {}
                    String dept = data[5].trim();
                    String position = data[6].trim();
                    String jobTitle = data[7].trim();
                    String company = data[8].trim();
                    
                    Employee newEmp;
                    
                    // 2. LOGIC: Decide if it's a Manager or Employee
                    // We check if the Job Title contains "Manager" OR "Senior"
                    if (jobTitle.toLowerCase().contains("manager") || position.equalsIgnoreCase("senior")) {
                        // Create Manager (Add dummy bonus/team size)
                        newEmp = new Manager(fn, ln, gender, email, salary, dept, position, jobTitle, company, 2500.0, 5);
                    } else {
                        // Create Regular Employee
                        newEmp = new Employee(fn, ln, gender, email, salary, dept, position, jobTitle, company);
                    }

                    // 3. Add to Master List
                    staffList.add(newEmp);
                    
                    // 4. Add to Correct Department Object (if exists)
                    boolean foundDept = false;
                    for (Department d : departments) {
                        if (dept.equalsIgnoreCase(d.getDeptName())) {
                            d.addStaff(newEmp);
                            foundDept = true;
                        }
                    }
                    // If department doesn't exist yet, create it dynamically
                    if (!foundDept) {
                        Department newDept = new Department(dept, "General Floor");
                        newDept.addStaff(newEmp);
                        departments.add(newDept);
                    }
                }
            }
            scanner.close();
            System.out.println(">> Loaded " + staffList.size() + " staff into " + departments.size() + " departments.");
            
        } catch (FileNotFoundException e) {
            System.out.println(">> Error: File not found.");
        }
    }

    // --- SORTING (Same as before) ---
    public void sortEmployees() {
        if (staffList.size() < 2) return;
        mergeSort(staffList);
        System.out.println(">> Sorted by Last Name.");
        displayAll();
    }

    private void mergeSort(ArrayList<Employee> list) {
        if (list.size() <= 1) return;
        int mid = list.size() / 2;
        ArrayList<Employee> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Employee> right = new ArrayList<>(list.subList(mid, list.size()));
        mergeSort(left);
        mergeSort(right);
        merge(list, left, right);
    }

    private void merge(ArrayList<Employee> list, ArrayList<Employee> left, ArrayList<Employee> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) list.set(k++, left.get(i++));
            else list.set(k++, right.get(j++));
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }

    // --- SEARCHING (Same as before) ---
    public void searchByLastName(String name) {
        sortEmployees();
        int index = binarySearch(name, 0, staffList.size() - 1);
        if (index != -1) System.out.println("FOUND: " + staffList.get(index));
        else System.out.println("Not Found.");
    }

    private int binarySearch(String target, int left, int right) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        String midName = staffList.get(mid).getLastName();
        int res = midName.compareToIgnoreCase(target);
        if (res == 0) return mid;
        if (res > 0) return binarySearch(target, left, mid - 1);
        return binarySearch(target, mid + 1, right);
    }

    // --- DISPLAY ---
    public void displayAll() {
        for (Employee e : staffList) {
            System.out.println(e);
        }
    }
    
    // --- DISPLAY HIERARCHY (Tree View) ---
    public void displayHierarchy() {
        System.out.println("\n--- ORGANIZATIONAL HIERARCHY (By Department) ---");
        for (Department d : departments) {
            d.displayStaff();
        }
    }
    
    // --- ADD MANUAL EMPLOYEE ---
    public void addEmployeeManually(Scanner scanner) {
        System.out.print("First Name: "); String fn = scanner.next();
        System.out.print("Last Name: "); String ln = scanner.next();
        System.out.print("Gender: "); String gen = scanner.next();
        System.out.print("Email: "); String em = scanner.next();
        System.out.print("Salary: "); double sal = scanner.nextDouble();
        System.out.print("Dept: "); String dep = scanner.next();
        System.out.print("Position: "); String pos = scanner.next();
        System.out.print("Job Title: "); String job = scanner.next();
        System.out.print("Company: "); String co = scanner.next();
        
        staffList.add(new Employee(fn, ln, gen, em, sal, dep, pos, job, co));
        System.out.println("Added.");
    }
}
