/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2;

/**
 *
 * @author james
 */
public abstract class Department {

    public enum Name { FASHION, TECHNOLOGY, LIVING }
    protected Name deptName;

    public Department(Name deptName) {
        this.deptName = deptName;
    }
    
    public abstract String getTeamGoal();

    @Override
    public String toString() { return deptName.toString(); }

    // --- INNER CLASSES (The Subtypes) ---
    
    public static class Technology extends Department {
        public Technology() { super(Name.TECHNOLOGY); }
        @Override public String getTeamGoal() { return "Sell Gadgets"; }
    }

    public static class Fashion extends Department {
        public Fashion() { super(Name.FASHION); }
        @Override public String getTeamGoal() { return "Latest Trends"; }
    }

    public static class Living extends Department {
        public Living() { super(Name.LIVING); }
        @Override public String getTeamGoal() { return "Home Comfort"; }
    }
}
