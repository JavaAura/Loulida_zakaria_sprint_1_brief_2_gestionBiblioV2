package com.library.management.model.user;

public class Professeur extends Utilisateur{
private String Departement ;
public Professeur (int id,String name,String email ,String Departement){
    super(id, name, email);
    this.Departement = Departement;

}

    public void setDepartement(String departement) {
        Departement = departement;
    }

    public String getDepartement() {
        return Departement;
    }

    public void displayInfo(){
    System.out.println("i'm a professeur");
}
}
