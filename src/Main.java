import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<User> users = new ArrayList<>();

        // Saisie manuelle des utilisateurs
        System.out.print("Entrez le nombre d'utilisateurs (N) : ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        for (int i = 1; i <= n; i++) {
            System.out.println("Saisie des informations pour l'utilisateur " + i + ":");
            System.out.print("ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            System.out.print("Email: ");
            String userEmail = scanner.nextLine();

            System.out.print("Password: ");
            String userPassword = scanner.nextLine();

            int roleId;
            while (true) {
                try {
                    System.out.print("Entrez l'ID du rôle: ");
                    roleId = Integer.parseInt(scanner.nextLine());
                    break; // Si la conversion réussit, sortir de la boucle
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un entier valide pour l'ID du rôle.");
                }
            }

            System.out.print("Nom du rôle: ");
            String roleName = scanner.nextLine();

            Role userRole = new Role(roleId, roleName);
            User user = new User(userId, userEmail, userPassword, userRole);
            users.add(user);
        }

        // Affichage des utilisateurs avec leurs rôles
        System.out.println("Liste des utilisateurs avec leurs rôles :");
        for (User user : users) {
            System.out.println("ID: " + user.getId() +
                    ", Email: " + user.getEmail() +
                    ", Password: " + user.getPassword() +
                    ", Hashed Password: " + user.getHashedPassword() +
                    ", Role ID: " + user.getRole().getId() +
                    ", Role Name: " + user.getRole().getName());
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convertir les octets en représentation hexadécimale
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte hashByte : hashBytes) {
                hexStringBuilder.append(String.format("%02x", hashByte));
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Gérer l'exception (relever, journaliser, etc.) en production
            return null;
        }
    }
}