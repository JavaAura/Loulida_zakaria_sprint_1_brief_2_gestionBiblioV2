package com.library.management.model.document;

import com.library.management.model.enums.Rule;

import java.time.LocalDate;

public abstract class Document {
    private int id; // Unique identifier for the document
    private String title;
    private String author;
    private LocalDate publicationDate;
    private String type;
    private Rule rule;

    public Document() {
    }

    // Constructor
 public Document(int id, String title, String author ,LocalDate publicationDate, Rule rule){
this.id= id;
     this.title = title ;
     this.author = author;
     this.publicationDate = publicationDate;
     this.rule =rule;
 }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return rule;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public abstract void displayInfo();
}
