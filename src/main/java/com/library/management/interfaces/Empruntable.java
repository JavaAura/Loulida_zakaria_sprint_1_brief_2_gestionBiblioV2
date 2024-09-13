package com.library.management.interfaces;

import com.library.management.model.Borrowing;

public interface Empruntable {

    void Emprunter (Borrowing borrowing);
    void Retourner(int id);
}
