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
    // The 9 fields from your text file
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private double salary;
    private String department;
    private String position;
    private String jobTitle;
    private String company;

    // Constructor
    public Employee(String firstName, String lastName, String gender, String email, 
                    double salary, String department, String position, String jobTitle, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.position = position;
        this.jobTitle = jobTitle;
        this.company = company;
    }

    // Required for searching by Name
    public String getLastName() {
        return lastName;
    }

    // Required for sorting (Alphabetical by Last Name)
    @Override
    public int compareTo(Employee other) {
        return this.lastName.compareToIgnoreCase(other.lastName);
    }

    // Pretty printing for the console
    @Override
    public String toString() {
        return String.format("%-12s %-12s | %-25s | %-15s", 
            lastName, firstName, jobTitle, company);
    }
}
