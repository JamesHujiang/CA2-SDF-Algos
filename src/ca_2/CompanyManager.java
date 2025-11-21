/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author james
 */
public class CompanyManager {
    private ArrayList<Employee> staffList;

    public CompanyManager() {
        this.staffList = new ArrayList<>();
    }

    public void addEmployee(Employee e) {
        staffList.add(e);
    }

    public void displayAll() {
        for (Employee e : staffList) {
            System.out.println(e);
        }
    }
    
    // Placeholder for your Merge Sort
    public void sortEmployees() {
        // We will implement Merge Sort here later
        Collections.sort(staffList); // Temporary cheat to test the menu
        System.out.println("Employees sorted!");
    }

    // Placeholder for Binary Search
    public void searchEmployee(int id) {
        System.out.println("Searching for ID: " + id);
    }
}
