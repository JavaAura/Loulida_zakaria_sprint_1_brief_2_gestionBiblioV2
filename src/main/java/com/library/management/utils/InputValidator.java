package com.library.management.utils;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class InputValidator {


    public static boolean validerISBN(String isbn) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(isbn);
        return matcher.matches();
    }

    public static boolean validerNumero(String numero) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }

    public static String obtenirISBNValide(Scanner scanner) {
        while (true) {
            System.out.print("ISBN : ");
            String isbn = scanner.nextLine();
            try {
                if (validerISBN(isbn)) {
                    return isbn;
                } else {
                    System.out.println("ISBN invalide. Veuillez entrer un ISBN au format correct.");
                }
            } catch (PatternSyntaxException e) {
                System.out.println("Erreur dans le pattern ISBN : " + e.getMessage());
            }
        }
    }

    public static int obtenirNumeroValide(Scanner scanner) {
        while (true) {
            System.out.print("Numéro : ");
            String numeroStr = scanner.nextLine();
            try {
                if (validerNumero(numeroStr)) {
                    return Integer.parseInt(numeroStr);
                } else {
                    System.out.println("Numéro invalide. Veuillez entrer un numéro valide.");
                }
            } catch (PatternSyntaxException e) {
                System.out.println("Erreur dans le pattern de numéro : " + e.getMessage());
            }
        }
    }


    public static String obtenirStringValide(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt + " : ");
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Ce champ ne peut pas être vide.");
            }
        } while (input.trim().isEmpty());
        return input;
    }

    public static LocalDate obtenirDateValide(Scanner scanner) {
        while (true) {
            System.out.print("Date de publication (AAAA-MM-JJ) : ");
            String dateStr = scanner.nextLine();
            try {
                return LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide. Veuillez entrer la date au format AAAA-MM-JJ.");
            }
        }
    }

    public static int obtenirNombreDePagesValide(Scanner scanner) {
        while (true) {
            System.out.print("Nombre de pages : ");
            if (scanner.hasNextInt()) {
                int nombreDePages = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                if (nombreDePages > 0) {
                    return nombreDePages;
                } else {
                    System.out.println("Le nombre de pages doit être supérieur à zéro.");
                }
            } else {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}

