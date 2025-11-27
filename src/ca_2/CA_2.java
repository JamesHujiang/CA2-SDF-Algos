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

    // The "Database" in  memory
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

                        // 1. The Header (Matches the width of the toString above)
                        // We add "%-4s" at the start for the numbered list (e.g., "1. ")
                        System.out.printf("%-4s %-12s %-12s [ %-12s | %-12s ] %s%n", 
                                "#", "LAST NAME", "FIRST NAME", "DEPARTMENT", "ROLE", "CONTRACT");
                        System.out.println("-------------------------------------------------------------------------------------");

                        // 2. The Loop
                        int limit = Math.min(20, staffList.size());
                        for(int i=0; i < limit; i++) {
                            // %-4d puts the number in a 4-char block (e.g., "1.  ")
                            System.out.printf("%-4s %s%n", (i + 1) + ".", staffList.get(i));
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

                        // CALL THE NEW METHOD
                        ArrayList<Employee> results = AlgorithmUtils.binarySearch(staffList, query);

                        if (!results.isEmpty()) {
                            System.out.println("\n--- FOUND " + results.size() + " RESULT(S) ---");
                            for (Employee e : results) {
                                System.out.println(e); // Uses the nice toString we made
                            }
                        } else {
                            System.out.println("User '" + query + "' not found.");
                        }
                        break;
                        
                    case ADD_NEW_RECORD:
                        System.out.println("\n--- ADD NEW EMPLOYEE ---");

                        // 1. Get Names
                        System.out.print("Enter First Name: ");
                        String newFirst = input.nextLine();
                        System.out.print("Enter Last Name: ");
                        String newLast = input.nextLine();

                        // 2. Get Department (with Validation)
                        Department newDept = null;
                        while (newDept == null) {
                            System.out.println("Departments: [1] TECHNOLOGY, [2] FASHION, [3] LIVING");
                            System.out.print("Select Department (1-3): ");
                            String dChoice = input.nextLine();

                            if (dChoice.equals("1")) newDept = new Department.Technology();
                            else if (dChoice.equals("2")) newDept = new Department.Fashion();
                            else if (dChoice.equals("3")) newDept = new Department.Living();
                            else System.out.println("Invalid choice. Try again.");
                        }

                        // 3. Get Role (with Validation)
                        Role newRole = null;
                        while (newRole == null) {
                            System.out.println("Roles: [1] EXECUTIVE, [2] SUPERVISOR, [3] ASSOCIATE");
                            System.out.print("Select Role (1-3): ");
                            String rChoice = input.nextLine();

                            if (rChoice.equals("1")) newRole = new Role.Executive();
                            else if (rChoice.equals("2")) newRole = new Role.Supervisor();
                            else if (rChoice.equals("3")) newRole = new Role.Associate();
                            else System.out.println("Invalid choice. Try again.");
                        }

                        // 4. Get Contract Type
                        Employee newEmp = null;
                        while (newEmp == null) {
                            System.out.println("Contract: [1] Full-Time, [2] Part-Time, [3] Seasonal");
                            System.out.print("Select Contract (1-3): ");
                            String cChoice = input.nextLine();

                            if (cChoice.equals("1")) newEmp = new Employee.FullTime(newFirst, newLast, newDept, newRole);
                            else if (cChoice.equals("2")) newEmp = new Employee.PartTime(newFirst, newLast, newDept, newRole);
                            else if (cChoice.equals("3")) newEmp = new Employee.Seasonal(newFirst, newLast, newDept, newRole);
                            else System.out.println("Invalid choice. Try again.");
                        }

                        // 5. Add to List
                        staffList.add(newEmp);

                        // REQUIREMENT MET: "Display all newly added records"
                        System.out.println("\n--- NEW RECORD ADDED SUCCESSFULLY ---");
                        // This calls the toString() method we fixed earlier, showing all details/columns
                        System.out.println(newEmp); 

                        // Mark list as Unsorted
                        isSorted = false; 
                        break;
                        
                    case DISPLAY_HIERARCHY:
                        System.out.println("Generating Organisation Chart...");

                        // STEP 1: Sort by Rank (Executives First)
                        // We use a lambda comparator here to sort by Rank Number (1 -> 3)
                        staffList.sort((e1, e2) -> Integer.compare(e1.getRole().getRank(), e2.getRole().getRank()));

                        // STEP 2: Create Tree and Fill it
                        BinaryTree orgTree = new BinaryTree();

                        // Insert top 20 people
                        int treeLimit = Math.min(20, staffList.size());
                        for (int i = 0; i < treeLimit; i++) {
                            orgTree.insert(staffList.get(i));
                        }

                        // STEP 3: Display
                        orgTree.printHierarchy();

                        // STEP 4: Stats
                        System.out.println("\n--- TREE STATS ---");
                        System.out.println("Total Nodes: " + orgTree.countNodes());
                        System.out.println("Tree Height: " + orgTree.getHeight());

                        // Reset 'isSorted' because we just messed up the Alphabetical order!
                        isSorted = false; 
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
