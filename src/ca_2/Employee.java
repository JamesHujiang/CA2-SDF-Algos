/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

/**
 *
 * @author james
 */
public class Employee implements Comparable<Employee> {
    private int id;
    private String firstName;
    private String lastName;
    private String department;
    private String email;

    // Constructor
    public Employee(int id, String firstName, String lastName, String department, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
    }

    // Getters (you need these for sorting/searching)
    public String getLastName() { return lastName; }
    public int getId() { return id; }

    // To String (for printing)
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + lastName + ", " + firstName + " | Dept: " + department;
    }

    // Comparable Interface (Crucial for Sorting!)
    @Override
    public int compareTo(Employee other) {
        // Sorts alphabetically by Last Name
        return this.lastName.compareToIgnoreCase(other.lastName);
    }
}
