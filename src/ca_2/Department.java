/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class Department {
    private String deptName;
    private String location;
    private List<Employee> staff;

    public Department(String deptName, String location) {
        this.deptName = deptName;
        this.location = location;
        this.staff = new ArrayList<>();
    }

    public void addStaff(Employee e) {
        staff.add(e);
    }

    public String getDeptName() { return deptName; }
    
    public void displayStaff() {
        System.out.println("\n--- Department: " + deptName + " (" + location + ") ---");
        for (Employee e : staff) {
            System.out.println(e);
        }
    }
}
