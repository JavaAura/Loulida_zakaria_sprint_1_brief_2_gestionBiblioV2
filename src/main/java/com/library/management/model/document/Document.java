package com.library.management.model.document;

public abstract class Document {
    private String id; // Unique identifier for the document
    private String title;
    private String author;
    private String publicationDate;

    // Constructor
 public Document(String ID, String title, String author ,String publicationDate){
     this.id = ID;
     this.title = title ;
     this.author = author;
     this.publicationDate = publicationDate;
 }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublicationDate() {
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
