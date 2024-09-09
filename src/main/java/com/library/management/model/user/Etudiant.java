package com.library.management.model.user;

public class Etudiant extends Utilisateur {
    private String studentId;

    public Etudiant(int id, String name,String email, String studentId) {
        super(id, name,email);
        this.studentId = studentId;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    @Override
    public void displayInfo() {
       System.out.println("i'm a user");
    }
}
