package com.library.management.dao;

import com.library.management.model.user.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void create(Utilisateur user) ;
    void update(Utilisateur user) ;
    void delete(int id) ;
    Utilisateur getUser(int id) ;
    List<Utilisateur> findAll() ;
}
