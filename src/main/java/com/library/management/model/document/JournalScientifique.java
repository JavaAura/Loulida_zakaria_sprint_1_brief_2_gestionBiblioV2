package com.library.management.model.document;

import com.library.management.model.enums.Rule;

import java.time.LocalDate;

public class JournalScientifique extends Document {
    private String domaineRecherche;


    public JournalScientifique(int id, String title, String author, LocalDate publicationDate, String domaineRecherche, Rule rule) {
        super( id ,title, author, publicationDate,rule);
        this.domaineRecherche = domaineRecherche;
    }

    public JournalScientifique( ) {
    }

    public String getDomaineRecherche() { return domaineRecherche; }
    public void setDomaineRecherche(String domaineRecherche) { this.domaineRecherche = domaineRecherche; }

    @Override
    public void displayInfo() {
        System.out.printf("|id : %-7d |%-30s | %-20s | Domaine de recherche :  %-8s  | %-15s |Rule %-15s  can borrow it | JournalScientifique|\n",getId() ,getTitle(), getAuthor(), domaineRecherche, getPublicationDate() ,getRule() );

    }
}
