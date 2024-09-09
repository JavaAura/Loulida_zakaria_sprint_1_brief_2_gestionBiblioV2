package com.library.management.model.document;

import java.time.LocalDate;

public abstract class Document {
    private int id; // Unique identifier for the document
    private String title;
    private String author;
    private LocalDate publicationDate;
    private String type;

    // Constructor
 public Document( String title, String author ,LocalDate publicationDate){

     this.title = title ;
     this.author = author;
     this.publicationDate = publicationDate;
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
