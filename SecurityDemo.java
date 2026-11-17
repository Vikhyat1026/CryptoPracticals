import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class SecurityDemo {
    static String sha256(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] raw = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder hex = new StringBuilder(raw.length * 2);
        for (byte b : raw) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    public static void main(String[] args) throws Exception {
        String msg = "Hello BTech Students";
        String origHash = sha256(msg);

        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        SecretKey key = kg.generateKey();

        Cipher aes = Cipher.getInstance("AES");
        aes.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherBytes = aes.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        String encText = Base64.getEncoder().encodeToString(cipherBytes);

        aes.init(Cipher.DECRYPT_MODE, key);
        String decText = new String(aes.doFinal(cipherBytes), StandardCharsets.UTF_8);

        String decHash = sha256(decText);

        System.out.println("Message  : " + msg);
        System.out.println("Encrypted: " + encText);
        System.out.println("Decrypted: " + decText);
        System.out.println();
        System.out.println("Original hash : " + origHash);
        System.out.println("Decrypted hash: " + decHash);

        if (origHash.equals(decHash)) {
            System.out.println("Verified: hashes match");
        } else {
            System.out.println("Failed: hash mismatch");
        }
    }
}
