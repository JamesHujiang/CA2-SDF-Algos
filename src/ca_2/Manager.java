/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

/**
 *
 * @author james
 */
public class Manager extends Employee {
    private double bonus;
    private int teamSize;

    // Constructor accepts all 9 fields PLUS bonus and teamSize
    public Manager(String firstName, String lastName, String gender, String email, 
                   double salary, String department, String position, String jobTitle, String company,
                   double bonus, int teamSize) {
        super(firstName, lastName, gender, email, salary, department, position, jobTitle, company);
        this.bonus = bonus;
        this.teamSize = teamSize;
    }

    @Override
    public String toString() {
        // Adds a [MANAGER] tag to the standard output
        return super.toString() + " [MANAGER BONUS: €" + bonus + "]";
    }
}
