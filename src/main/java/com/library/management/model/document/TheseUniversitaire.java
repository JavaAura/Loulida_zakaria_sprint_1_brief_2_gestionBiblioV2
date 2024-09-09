package com.library.management.model.document;

import java.time.LocalDate;

public class TheseUniversitaire extends Document {
    private String université;
    private String domaine;

    public TheseUniversitaire( String title, String author, LocalDate publicationDate, String université, String domaine) {
        super( title, author, publicationDate);
        this.université = université;
        this.domaine = domaine;
    }

    public String getUniversité() { return université; }
    public void setUniversité(String université) { this.université = université; }

    public String getDomaine() { return domaine; }
    public void setDomaine(String domaine) { this.domaine = domaine; }

    @Override
    public void displayInfo() {
        System.out.printf("| %-30s | %-20s | Domaine:  %-8s  | %-15s |%-15s | ThèseUniversitaire|\n", getTitle(), getAuthor(), getDomaine(),getUniversité(), getPublicationDate());

    }
}
