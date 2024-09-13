package com.library.management.model;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int userId;
    private int documentId;


    public Reservation(int id, int userId, int documentId) {
        this.id = id;
        this.userId = userId;
        this.documentId = documentId;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }



    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", documentId=" + documentId +

                '}';
    }
}
