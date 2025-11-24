/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_2;

import java.util.Scanner;

/**
 *
 * @author james
 */
public class CA_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CompanyManager company = new CompanyManager();
        
        // Make sure this path is correct!
        String filename = "Applicants_Form - Sample data file for read.txt";
        
        System.out.println("Initializing System...");
        company.loadEmployeesFromFile(filename);

        boolean running = true;
        
        while (running) {
            System.out.println("\n===========================");
            System.out.println("   CORPORATE SYSTEM MENU   ");
            System.out.println("===========================");
            System.out.println("1. Display All Staff");
            System.out.println("2. Sort Staff (Merge Sort)");
            System.out.println("3. Search Staff (Binary Search)");
            System.out.println("4. Add New Employee");
            System.out.println("5. Show Hierarchy (Tree/Dept View)"); // <--- New Line
            System.out.println("6. Exit");
            System.out.print(">> Enter Choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.next(); 
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clean buffer

            switch (choice) {
                case 1:
                    company.displayAll();
                    break;
                case 2:
                    company.sortEmployees();
                    break;
                case 3:
                    System.out.print("Enter Last Name to Search: ");
                    String query = scanner.nextLine();
                    company.searchByLastName(query);
                    break;
                case 4:
                    company.addEmployeeManually(scanner);
                    break;
                case 5:
                    // This calls the new Hierarchy method in CompanyManager
                    company.displayHierarchy(); 
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
