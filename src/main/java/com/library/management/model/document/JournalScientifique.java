package com.library.management.model.document;

import java.time.LocalDate;

public class JournalScientifique extends Document {
    private String domaineRecherche;

    public JournalScientifique( String title, String author, LocalDate publicationDate, String domaineRecherche) {
        super( title, author, publicationDate);
        this.domaineRecherche = domaineRecherche;
    }

    public String getDomaineRecherche() { return domaineRecherche; }
    public void setDomaineRecherche(String domaineRecherche) { this.domaineRecherche = domaineRecherche; }

    @Override
    public void displayInfo() {
        System.out.printf("| %-30s | %-20s | Domaine de recherche :  %-8s  | %-15s | JournalScientifique|\n", getTitle(), getAuthor(), domaineRecherche, getPublicationDate());

    }
}
