package com.library.management.model.document;

import com.library.management.model.enums.Rule;

import java.time.LocalDate;

public class TheseUniversitaire extends Document {
    private String université;
    private String domaine;


    public TheseUniversitaire(int id , String title, String author, LocalDate publicationDate, String université, String domaine, Rule rule) {
        super( id, title, author, publicationDate,rule);
        this.université = université;
        this.domaine = domaine;
    }

    public TheseUniversitaire() {

    }
//    public TheseUniversitaire( String title, String author, LocalDate publicationDate, String université, String domaine) {
//        super( title, author, publicationDate);
//        this.université = université;
//        this.domaine = domaine;
//    }

    public String getUniversité() { return université; }
    public void setUniversité(String université) { this.université = université; }

    public String getDomaine() { return domaine; }
    public void setDomaine(String domaine) { this.domaine = domaine; }

    @Override
    public void displayInfo() {
        System.out.printf("| id : %-7d | %-30s | %-20s | Domaine:  %-8s  | %-15s |%-15s |Rule %-15s  can borrow it | ThèseUniversitaire|\n",getId(), getTitle(), getAuthor(), getDomaine(),getUniversité(), getPublicationDate(),getRule());

    }
}
