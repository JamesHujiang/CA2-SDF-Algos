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
    
    private ArrayList<Employee> staffList;

    public CompanyManager() {
        this.staffList = new ArrayList<>();
    }

    // --- 1. FILE READING (Matches your specific file format) ---
    public void loadEmployeesFromFile(String filename) {
        staffList.clear(); 
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Skip the Header Row ("First name, Last name...")
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Split by comma (CSV)
                String[] data = line.split(",");

                // Only add if we have all 9 columns
                if (data.length >= 9) {
                    String fn = data[0].trim();
                    String ln = data[1].trim();
                    String gen = data[2].trim();
                    String email = data[3].trim();
                    // Handle salary parsing safely
                    double sal = 0.0;
                    try { sal = Double.parseDouble(data[4].trim()); } catch (Exception e) { }
                    
                    String dept = data[5].trim();
                    String pos = data[6].trim();
                    String job = data[7].trim();
                    String co = data[8].trim();

                    staffList.add(new Employee(fn, ln, gen, email, sal, dept, pos, job, co));
                }
            }
            scanner.close();
            System.out.println(">> Successfully loaded " + staffList.size() + " employees.");
            
        } catch (FileNotFoundException e) {
            System.out.println(">> ERROR: File not found! Make sure '" + filename + "' is in the project folder.");
        }
    }

    // --- 2. DISPLAY ---
    public void displayAll() {
        if (staffList.isEmpty()) {
            System.out.println(">> List is empty.");
            return;
        }
        System.out.println("\n--- EMPLOYEE LIST ---");
        for (Employee e : staffList) {
            System.out.println(e);
        }
    }

    // --- 3. SORTING (Recursive Merge Sort) ---
    public void sortEmployees() {
        if (staffList.size() < 2) {
            System.out.println(">> Not enough data to sort.");
            return;
        }
        mergeSort(staffList);
        System.out.println(">> Employees sorted by Last Name.");
        displayAll(); // Show result immediately
    }

    // Recursive Helper
    private void mergeSort(ArrayList<Employee> list) {
        if (list.size() <= 1) return; // Base case

        int mid = list.size() / 2;
        ArrayList<Employee> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Employee> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);
        merge(list, left, right);
    }

    // Merge Helper
    private void merge(ArrayList<Employee> list, ArrayList<Employee> left, ArrayList<Employee> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }

    // --- 4. SEARCHING (Binary Search by Last Name) ---
    public void searchByLastName(String name) {
        // Binary Search works ONLY on sorted lists
        sortEmployees(); 
        
        int index = binarySearch(name, 0, staffList.size() - 1);
        
        if (index != -1) {
            System.out.println("\n>> FOUND MATCH: " + staffList.get(index));
        } else {
            System.out.println("\n>> No employee found with Last Name: " + name);
        }
    }

    private int binarySearch(String target, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;
        String midName = staffList.get(mid).getLastName();
        
        int res = midName.compareToIgnoreCase(target);

        if (res == 0) return mid; // Found
        if (res > 0) return binarySearch(target, left, mid - 1); // Search Left
        return binarySearch(target, mid + 1, right); // Search Right
    }
}
