package com.library.management.model.user;

import java.time.LocalDate;

public class Professeur extends Utilisateur {
    private String domaineRecherche;

    public Professeur(String nom, String prenom, String email, String motDePasse, LocalDate dateInscription, String domaineRecherche) {
        super(nom, prenom, email, motDePasse, dateInscription);
        this.domaineRecherche = domaineRecherche;
    }

    // Getters and Setters
    public String getDomaineRecherche() {
        return domaineRecherche;
    }

    public void setDomaineRecherche(String domaineRecherche) {
        this.domaineRecherche = domaineRecherche;
    }

    @Override
    public String toString() {
        return "Professur{ id :"+ getId() + "  name  :"+ getNom()  + getPrenom()
                + "  domaineRecherche='" + domaineRecherche + '\'' + "  DateInscription :  " + getDateInscription()+
                '}';
    }

    public void displayInfo(){

        System.out.println("******************************************************"  );
        System.out.println("Professeur id :"+ getId()  );
        System.out.println("name  :"+ getNom() + "       prenom : " + getPrenom() );
        System.out.println("domaine de recherche  :"+ getDomaineRecherche() + "   DateInscription :  " + getDateInscription() );

    }
}
