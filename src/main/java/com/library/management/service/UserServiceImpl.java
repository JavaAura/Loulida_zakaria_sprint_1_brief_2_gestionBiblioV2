package com.library.management.service;

import com.library.management.dao.EtudiantDAO;
import com.library.management.dao.ProfesseurDAO;
import com.library.management.model.user.Etudiant;
import com.library.management.model.user.Professeur;
import com.library.management.model.user.Utilisateur;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private EtudiantDAO etudiantDAO;
    private ProfesseurDAO professeurDAO;

    public UserServiceImpl() {
        this.etudiantDAO = new EtudiantDAO();
        this.professeurDAO = new ProfesseurDAO();
    }

    @Override
    public void create(Utilisateur user) {
        if (user instanceof Etudiant) {
            etudiantDAO.create(user);
        } else if (user instanceof Professeur) {
            professeurDAO.create(user);
        } else {
            throw new IllegalArgumentException("Unsupported user type.");
        }
    }

    @Override
    public void update(Utilisateur user) {
        if (user instanceof Etudiant) {
            etudiantDAO.update(user);
        } else if (user instanceof Professeur) {
            professeurDAO.update(user);
        } else {
            throw new IllegalArgumentException("Unsupported user type.");
        }
    }

    @Override
    public void delete(int id) {
        // Assuming we have a way to identify the type of user.
        // You might need additional logic to handle this properly.
        Optional<Utilisateur> user = getUser(id);
        if (user.isPresent()) {
            if (user.get() instanceof Etudiant) {
                etudiantDAO.delete(id);
            } else if (user.get() instanceof Professeur) {
                professeurDAO.delete(id);
            }
        }
    }

    @Override
    public Optional<Utilisateur> getUser(int id) {
        System.out.println("id : " + id);
        Utilisateur user = etudiantDAO.getUser(id);

        if (user == null) {
            user = professeurDAO.getUser(id);
        }
        return Optional.ofNullable(user); // Wrap the result in Optional
    }


    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> utilisateurs = etudiantDAO.findAll();
        utilisateurs.addAll(professeurDAO.findAll());
        return utilisateurs;
    }
}
