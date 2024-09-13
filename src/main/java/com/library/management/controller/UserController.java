package com.library.management.controller;

import com.library.management.model.user.Etudiant;
import com.library.management.model.user.Professeur;
import com.library.management.model.user.Utilisateur;
import com.library.management.service.UserService;
import com.library.management.service.UserServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserController {
    private UserService userService;
    private Scanner scanner;

    public UserController() {
        this.userService = new UserServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void addUser() {
        System.out.println("Enter user type to add (1 for Etudiant, 2 for Professeur): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter user name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter user surname: ");
        String surname = scanner.nextLine().trim();

        System.out.println("Enter user email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Enter user password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Enter date of registration (yyyy-MM-dd): ");
        LocalDate registrationDate = LocalDate.parse(scanner.nextLine().trim());

        switch (choice) {
            case 1:
                System.out.println("Enter education level: ");
                String educationLevel = scanner.nextLine().trim();
                Etudiant etudiant = new Etudiant(name, surname, email, password, registrationDate, educationLevel);
                userService.create(etudiant);
                break;
            case 2:
                System.out.println("Enter research domain: ");
                String researchDomain = scanner.nextLine().trim();
                Professeur professeur = new Professeur(name, surname, email, password, registrationDate, researchDomain);
                userService.create(professeur);
                break;
            default:
                System.out.println("Invalid user type.");
        }
    }

    public void updateUser() {
        System.out.println("Enter user ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter new surname: ");
        String surname = scanner.nextLine().trim();

        System.out.println("Enter new email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Enter new password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Enter new date of registration (yyyy-MM-dd): ");
        LocalDate registrationDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.println("Enter user type (Etudiant, Professeur): ");
        String type = scanner.nextLine().trim();

        switch (type) {
            case "Etudiant":
                System.out.println("Enter new education level: ");
                String educationLevel = scanner.nextLine().trim();
                Etudiant etudiant = new Etudiant(name, surname, email, password, registrationDate, educationLevel);
                etudiant.setId(id);
                userService.update(etudiant);
                break;
            case "Professeur":
                System.out.println("Enter new research domain: ");
                String researchDomain = scanner.nextLine().trim();
                Professeur professeur = new Professeur(name, surname, email, password, registrationDate, researchDomain);
                professeur.setId(id);
                userService.update(professeur);
                break;
            default:
                System.out.println("Invalid user type.");
        }
    }
    public void getUser() {
        System.out.println("Enter user ID: ");
        int id = scanner.nextInt();

        Optional<Utilisateur> user = userService.getUser(id);

        if (user.isPresent()) {
            Utilisateur utilisateur = user.get();  // Unwrap the Optional
            System.out.println(utilisateur);  // This will call toString() method of Professeur or Etudiant
        } else {
            System.out.println("User not found.");
        }
    }




    public void deleteUser() {
        System.out.println("Enter user ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        userService.delete(id);
        System.out.println("User deleted successfully.");
    }

    public void listUsers() {
        List<Utilisateur> users = userService.findAll();
        users.forEach(Utilisateur::displayInfo);
    }
}
