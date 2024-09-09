package com.library.management.model.document;

import java.time.LocalDate;

public class Livre extends Document {
    private String isbn;



    public Livre( String title, String author, LocalDate publicationDate, String isbn) {
        super( title, author, publicationDate);
        this.isbn = isbn;
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public void displayInfo() {
        System.out.printf("| %-30s | %-20s | ISBN: %-10s | %-15s | Livre   |\n", getTitle(), getAuthor(), getIsbn(), getPublicationDate());

    }
}
