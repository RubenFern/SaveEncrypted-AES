import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
public class AES
{
    private static final String KEY = "Llavedeseguridad";

    public SecretKeySpec CreateKey(String key)
    {
        try
        {
            byte[] bytesKey = key.getBytes("UTF-8"); // I get the bytes of the key
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytesKey = md.digest(bytesKey); // Add more bytes to the previous byte array
            bytesKey = Arrays.copyOf(bytesKey, 16);
            SecretKeySpec secretKey = new SecretKeySpec(bytesKey, "AES"); // I use the class that implements the Key class

            return secretKey;
        } catch (Exception e)
        {
            return null;
        }
    }

    public String Encrypt(String password)
    {
        try
        {
            // Configuration of Cipher
            SecretKeySpec key = CreateKey(KEY); // I get the security key for encryption
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // Configure encryption to use 128-bit AES with padding
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] bytesPassword = password.getBytes("UTF-8"); // // I get the bytes of the password
            byte[] bytesEncrypt = cipher.doFinal(bytesPassword); // I encrypt the password
            bytesEncrypt = Base64.getEncoder().encode(bytesEncrypt); // I use Base64 encoding for the encrypted bytes.
            String encrypt = new String(bytesEncrypt); // I transform them into a string

            return encrypt;
        } catch (Exception e)
        {
            return e.getMessage();
        }
    }

    public String Decrypt(String password)
    {
        try
        {
            // Configuration of Cipher
            SecretKeySpec key = CreateKey(KEY); // I get the security key for decryption
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // Configure decryption to use 128-bit AES with padding
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] bytesPassword = Base64.getDecoder().decode(password);  // Base64 decoding
            byte[] bytesDecrypt = cipher.doFinal(bytesPassword); // Decryption
            String decrypt = new String(bytesDecrypt); // I transform then into a String

            return decrypt;
        } catch (Exception e)
        {
            return e.getMessage();
        }
    }
}
