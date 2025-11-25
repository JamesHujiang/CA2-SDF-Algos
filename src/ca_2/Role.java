/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

/**
 *
 * @author james
 */
public abstract class Role {

    public enum Name { EXECUTIVE, SUPERVISOR, ASSOCIATE }
    protected Name roleName;
    protected int rank;

    public Role(Name roleName, int rank) {
        this.roleName = roleName;
        this.rank = rank;
    }
    
    public int getRank() { return rank; }

    @Override
    public String toString() { return roleName.toString(); }

    // --- INNER CLASSES (The Subtypes) ---

    public static class Executive extends Role {
        public Executive() { super(Name.EXECUTIVE, 1); }
    }

    public static class Supervisor extends Role {
        public Supervisor() { super(Name.SUPERVISOR, 2); }
    }

    public static class Associate extends Role {
        public Associate() { super(Name.ASSOCIATE, 3); }
    }
}
