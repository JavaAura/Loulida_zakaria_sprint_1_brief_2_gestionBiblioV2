package com.library.management.model.document;

import com.library.management.model.enums.Rule;

import java.time.LocalDate;

public class Magazine extends Document {
    private String numero;

    public Magazine(int id, String title, String author, LocalDate publicationDate, String numero, Rule rule) {
        super(id, title, author, publicationDate,rule);
        this.numero = numero;
    }

    public Magazine(){

    }
//    public Magazine( String title, String author, LocalDate publicationDate, String numero) {
//        super( title, author, publicationDate);
//        this.numero = numero;
//    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    @Override
    public void displayInfo() {
        System.out.printf("| id : %-7d |%-30s | %-20s | Numéro: %-8s | %-15s |Rule %-15s  can borrow it | Magazine|\n", getId(),getTitle(), getAuthor(), getNumero(), getPublicationDate(),getRule());
    }

}
