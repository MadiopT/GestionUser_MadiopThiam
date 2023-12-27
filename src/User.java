import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private int id;
    private String email;
    private String password;
    private String hashedPassword;
    private Role role;

    public User(int id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.hashedPassword = hashPassword(password);
        this.role = role;
    }

    private String hashPassword(String password) {
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

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public Role getRole() {
        return role;
    }
}
