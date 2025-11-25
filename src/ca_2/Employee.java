/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

/**
 *
 * @author james
 */
public abstract class Employee implements Comparable<Employee> {
    
    protected String firstName;
    protected String lastName;
    protected String email;
    protected Department department; 
    protected Role role;

    public Employee(String firstName, String lastName, Department department, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.role = role;
        this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@store.com";
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Department getDepartment() { return department; }
    public Role getRole() { return role; }

    public abstract String getContractType();

    @Override
    public String toString() {
        return String.format("%-12s %-12s [ %-12s | %-12s ] - %s", 
                lastName, firstName, 
                department.toString(), role.toString(), 
                getContractType());
    }

    @Override
    public int compareTo(Employee other) {
        return this.lastName.compareToIgnoreCase(other.lastName);
    }

    // --- INNER CLASSES (The Subtypes) ---

    public static class FullTime extends Employee {
        public FullTime(String f, String l, Department d, Role r) { super(f, l, d, r); }
        @Override public String getContractType() { return "Full-Time"; }
    }

    public static class PartTime extends Employee {
        public PartTime(String f, String l, Department d, Role r) { super(f, l, d, r); }
        @Override public String getContractType() { return "Part-Time"; }
    }

    public static class Seasonal extends Employee {
        public Seasonal(String f, String l, Department d, Role r) { super(f, l, d, r); }
        @Override public String getContractType() { return "Seasonal"; }
    }
}
