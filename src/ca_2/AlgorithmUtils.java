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
public class AlgorithmUtils {

    // --- MERGE SORT (Recursive) ---
    // We use a wrapper method to make calling it from Main easier
    public static void mergeSort(ArrayList<Employee> list) {
        if (list.size() <= 1) return; // Base case

        // Split
        int mid = list.size() / 2;
        ArrayList<Employee> left = new ArrayList<>();
        ArrayList<Employee> right = new ArrayList<>();

        for (int i = 0; i < mid; i++) left.add(list.get(i));
        for (int i = mid; i < list.size(); i++) right.add(list.get(i));

        // Recursion
        mergeSort(left);
        mergeSort(right);

        // Merge back
        merge(list, left, right);
    }

    private static void merge(ArrayList<Employee> result, ArrayList<Employee> left, ArrayList<Employee> right) {
        result.clear();
        int i = 0, j = 0;
        
        // Compare and add the smaller item (Alphabetical by Last Name)
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }
        
        // Add remaining items
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
    }

    // --- BINARY SEARCH ---
    // Returns the Employee object if found, or null if not found
    public static Employee binarySearch(ArrayList<Employee> list, String targetLastName) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Employee midEmp = list.get(mid);
            int comparison = midEmp.getLastName().compareToIgnoreCase(targetLastName);

            if (comparison == 0) {
                return midEmp; // Found!
            } else if (comparison < 0) {
                low = mid + 1; // Look in right half
            } else {
                high = mid - 1; // Look in left half
            }
        }
        return null; // Not found
    }
}
