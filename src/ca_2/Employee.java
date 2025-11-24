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
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String email;
    protected double salary;
    protected String department;
    protected String position;
    protected String jobTitle;
    protected String company;

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

    public String getLastName() { return lastName; }
    public String getDepartmentName() { return department; }

    @Override
    public int compareTo(Employee other) {
        return this.lastName.compareToIgnoreCase(other.lastName);
    }

    @Override
    public String toString() {
        // Displays key info: Name, Job, Dept, Company
        return String.format("%-12s %-12s | %-20s | %-15s | %s", 
            lastName, firstName, jobTitle, department, company);
    }
}
