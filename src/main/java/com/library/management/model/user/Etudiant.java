package com.library.management.model.user;

import java.time.LocalDate;

public class Etudiant extends Utilisateur {
    private String niveauEtudes;

    public Etudiant(String nom, String prenom, String email, String motDePasse, LocalDate dateInscription, String niveauEtudes) {
        super(nom, prenom, email, motDePasse, dateInscription);
        this.niveauEtudes = niveauEtudes;
    }

    // Getters and Setters
    public String getNiveauEtudes() {
        return niveauEtudes;
    }

    public void setNiveauEtudes(String niveauEtudes) {
        this.niveauEtudes = niveauEtudes;
    }


    @Override
    public String toString() {
        return "Etudiant { id :"+ getId() + "   name  :"+ getNom() +" " + getPrenom() +
                "  niveauEtudes='" + niveauEtudes + "  DateInscription :  " + getDateInscription()+
        '}';
    }

    public  void displayInfo(){
        System.out.println("******************************************"  );
        System.out.println("Etudiant id :"+ getId()  );
        System.out.println("name  :"+ getNom() +"  " + getPrenom() );
        System.out.println("Niveau d'etudes  :"+ getNiveauEtudes() + "         DateInscription :  " + getDateInscription() );

    }

    public  void affiche() {
        System.out.printf(""+getEmail());

    }
}
