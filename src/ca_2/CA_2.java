/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
public class CA_2 {

    // The "Database" in memory
    private static ArrayList<Employee> staffList = new ArrayList<>();
    
    // The Flag (Robustness Requirement)
    private static boolean isSorted = false;

    // The Menu Enum (Nested here to save file count)
    public enum MenuOption {
        SORT_EMPLOYEES,
        SEARCH_EMPLOYEE,
        ADD_NEW_RECORD,
        DISPLAY_HIERARCHY,
        EXIT
    }

    public static void main(String[] args) {
        loadDataFromFile();
        
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            
            try {
                int choice = Integer.parseInt(input.nextLine());
                
                // Validating Input Range
                if (choice < 1 || choice > MenuOption.values().length) {
                    System.out.println("Invalid option.");
                    continue;
                }

                // Mapping int to Enum
                MenuOption selected = MenuOption.values()[choice - 1];

                switch (selected) {
                    case SORT_EMPLOYEES:
                        System.out.println("Sorting employees by Last Name...");
                        AlgorithmUtils.mergeSort(staffList);
                        isSorted = true;

                        System.out.println("\n--- TOP 20 STAFF (A-Z) ---");
                        // Loop up to 20, or the list size (whichever is smaller to avoid errors)
                        int limit = Math.min(20, staffList.size());

                        for(int i=0; i < limit; i++) {
                            // Print index + 1 so it looks like a numbered list (1-20)
                            System.out.println((i + 1) + ". " + staffList.get(i));
                        }
                        break;
                       
                    case SEARCH_EMPLOYEE:
                        if (!isSorted) {
                            System.out.println("List must be sorted first! Sorting now...");
                            AlgorithmUtils.mergeSort(staffList);
                            isSorted = true;
                        }
                        System.out.print("Enter Last Name to search: ");
                        String query = input.nextLine();

                        Employee result = AlgorithmUtils.binarySearch(staffList, query);

                        if (result != null) {
                            System.out.println("FOUND: " + result);
                        } else {
                            System.out.println("User '" + query + "' not found.");
                        }
                        break;
                        
                    case ADD_NEW_RECORD:
                        // TODO: Add logic
                        System.out.println("Feature coming soon...");
                        isSorted = false; // List is broken now
                        break;
                        
                    case DISPLAY_HIERARCHY:
                        // TODO: Call BinaryTree builder
                        System.out.println("Building Tree...");
                        break;
                        
                    case EXIT:
                        running = false;
                        System.out.println("Goodbye.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- DEPARTMENT STORE SYSTEM ---");
        int i = 1;
        for (MenuOption opt : MenuOption.values()) {
            System.out.println(i + ". " + opt);
            i++;
        }
    }

    private static void loadDataFromFile() {
        try {
            Scanner fileScanner = new Scanner(new File("Applicants_Form.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                
                // Data: [0]First, [1]Last, [2]Dept, [3]Role, [4]Contract
                String firstName = data[0];
                String lastName = data[1];
                String deptStr = data[2];
                String roleStr = data[3];
                String typeStr = data[4];

                // Factory Logic: Create the right objects
                Department dept = null;
                if (deptStr.equalsIgnoreCase("TECHNOLOGY")) dept = new Department.Technology();
                else if (deptStr.equalsIgnoreCase("FASHION")) dept = new Department.Fashion();
                else dept = new Department.Living();

                Role role = null;
                if (roleStr.equalsIgnoreCase("EXECUTIVE")) role = new Role.Executive();
                else if (roleStr.equalsIgnoreCase("SUPERVISOR")) role = new Role.Supervisor();
                else role = new Role.Associate();

                Employee emp = null;
                if (typeStr.equalsIgnoreCase("FullTime")) emp = new Employee.FullTime(firstName, lastName, dept, role);
                else if (typeStr.equalsIgnoreCase("PartTime")) emp = new Employee.PartTime(firstName, lastName, dept, role);
                else emp = new Employee.Seasonal(firstName, lastName, dept, role);

                staffList.add(emp);
            }
            System.out.println("Loaded " + staffList.size() + " employees.");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found!");
        }
    }
}
