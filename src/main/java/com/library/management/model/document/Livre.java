package com.library.management.model.document;

import com.library.management.model.enums.Rule;

import java.time.LocalDate;

public class Livre extends Document {
    private String isbn;
    public Livre() {

    }
    public Livre(int id, String title, String author, LocalDate publicationDate, String isbn, Rule rule) {
        super(id, title, author, publicationDate,rule);
        this.isbn = isbn;
    }


//
//    public Livre( String title, String author, LocalDate publicationDate, String isbn) {
//        super( title, author, publicationDate);
//        this.isbn = isbn;
//    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public void displayInfo() {
        System.out.printf("|  id : %-7d  |%-30s | %-20s | ISBN: %-10s | %-15s |Rule %-15s  can borrow it | Livre   |\n",getId(), getTitle(), getAuthor(), getIsbn(), getPublicationDate(),getRule());

    }
}
