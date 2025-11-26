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
    // Returns a LIST of matches (to handle duplicates like multiple "Smiths")
    public static ArrayList<Employee> binarySearch(ArrayList<Employee> list, String targetLastName) {
        ArrayList<Employee> results = new ArrayList<>();
        
        int low = 0;
        int high = list.size() - 1;
        int index = -1;

        // 1. Standard Binary Search to find ONE match
        while (low <= high) {
            int mid = (low + high) / 2;
            Employee midEmp = list.get(mid);
            int comparison = midEmp.getLastName().compareToIgnoreCase(targetLastName);

            if (comparison == 0) {
                index = mid; // Found one!
                break; 
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // If no match found, return empty list
        if (index == -1) return results;

        // 2. Expand Left (Check previous records)
        int temp = index;
        while (temp >= 0 && list.get(temp).getLastName().equalsIgnoreCase(targetLastName)) {
            results.add(list.get(temp));
            temp--;
        }
        
        // Note: The loop above adds them in reverse order (Middle, Left 1, Left 2...)
        // But since we want ALL matches, we need to check the RIGHT side too.
        // A cleaner way is to find the FIRST occurrence and then iterate forward.
        
        // --- BETTER STRATEGY: Find First Occurrence Logic ---
        // Let's stick to the simpler expansion for now, but clear the list first to avoid duplicates
        results.clear();
        
        // Find the absolute first occurrence
        int start = index;
        while (start > 0 && list.get(start - 1).getLastName().equalsIgnoreCase(targetLastName)) {
            start--;
        }
        
        // Now add everyone from 'start' until the name changes
        while (start < list.size() && list.get(start).getLastName().equalsIgnoreCase(targetLastName)) {
            results.add(list.get(start));
            start++;
        }

        return results;
    }
}
