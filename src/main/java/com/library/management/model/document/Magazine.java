package com.library.management.model.document;

public class Magazine extends Document {
    private String numero;

    public Magazine(String id, String title, String author, String publicationDate, String numero) {
        super(id, title, author, publicationDate);
        this.numero = numero;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    @Override
    public void displayInfo() {
        System.out.printf("| %-30s | %-20s | Numéro: %-8d | %-15s | Magazine|\n", getTitle(), getAuthor(), numero, getPublicationDate());

    }
}
