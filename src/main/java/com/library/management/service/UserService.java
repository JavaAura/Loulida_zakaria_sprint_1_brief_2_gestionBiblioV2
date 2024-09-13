package com.library.management.service;

import com.library.management.model.user.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void create(Utilisateur user);
    void update(Utilisateur user);
    void delete(int id);
    Optional<Utilisateur> getUser(int id);
    List<Utilisateur> findAll();
}
