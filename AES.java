import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class AES {
    public static void main(String[] args) throws Exception {
        String msg = "Hello BTech Students";

        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        SecretKey key = kg.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        String encB64 = Base64.getEncoder().encodeToString(enc);

        cipher.init(Cipher.DECRYPT_MODE, key);
        String dec = new String(cipher.doFinal(enc), StandardCharsets.UTF_8);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(msg.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        System.out.println("Original : " + msg);
        System.out.println("Encrypted: " + encB64);
        System.out.println("Decrypted: " + dec);
        System.out.println("SHA-256  : " + sb);
    }
}
