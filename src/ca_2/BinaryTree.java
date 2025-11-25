/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author james
 */
public class BinaryTree {
    
    // --- INNER CLASS: NODE ---
    // Hidden inside the Tree class to reduce file count
    private static class Node {
        Employee data;
        Node left;
        Node right;

        public Node(Employee data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    // -------------------------

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    // 1. Insertion Logic (Level Order)
    public void insert(Employee emp) {
        Node newNode = new Node(emp);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.left == null) {
                current.left = newNode;
                break;
            } else {
                queue.add(current.left);
            }

            if (current.right == null) {
                current.right = newNode;
                break;
            } else {
                queue.add(current.right);
            }
        }
    }

    // 2. Display Logic
    public void printHierarchy() {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        System.out.println("\n--- ORGANISATION HIERARCHY ---");

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + ": ");

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                System.out.print("[" + current.data.getLastName() + "-" + current.data.getRole().toString() + "] ");

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            System.out.println();
            level++;
        }
    }

    // 3. Stats
    public int countNodes() { return countNodes(root); }
    private int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public int getHeight() { return getHeight(root); }
    private int getHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}
