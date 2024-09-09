package com.library.management.model.document;

import java.time.LocalDate;

public class Magazine extends Document {
    private String numero;

    public Magazine( String title, String author, LocalDate publicationDate, String numero) {
        super( title, author, publicationDate);
        this.numero = numero;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    @Override
    public void displayInfo() {
        System.out.printf("| %-30s | %-20s | Numéro: %-8s | %-15s | Magazine|\n", getTitle(), getAuthor(), getNumero(), getPublicationDate());
    }

}
