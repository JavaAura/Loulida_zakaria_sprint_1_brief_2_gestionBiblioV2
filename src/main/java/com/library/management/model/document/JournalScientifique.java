package com.library.management.model.document;

public class JournalScientifique extends Document {
    private String domaineRecherche;

    public JournalScientifique(String id, String title, String author, String publicationDate, String domaineRecherche) {
        super(id, title, author, publicationDate);
        this.domaineRecherche = domaineRecherche;
    }

    public String getDomaineRecherche() { return domaineRecherche; }
    public void setDomaineRecherche(String domaineRecherche) { this.domaineRecherche = domaineRecherche; }

    @Override
    public void displayInfo() {
        System.out.printf("| %-30s | %-20s | Numéro: %-8d | %-15s | JournalScientifique|\n", getTitle(), getAuthor(), domaineRecherche, getPublicationDate());

    }
}
