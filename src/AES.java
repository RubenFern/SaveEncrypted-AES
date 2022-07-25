import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
public class AES
{
    final static String KEY = "Llavedeseguridad";

    public SecretKeySpec CreateClave(String key)
    {
        try
        {
            byte[] str = key.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            str = md.digest(str);
            str = Arrays.copyOf(str, 16);
            SecretKeySpec scs = new SecretKeySpec(str, "AES");

            return scs;
        } catch (Exception e)
        {
            return null;
        }
    }

    public String Encrypt(String password)
    {
        try
        {
            SecretKeySpec key = CreateClave(KEY);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] str = password.getBytes("UTF-8");
            byte[] encrypt = cipher.doFinal(str);
            encrypt = Base64.getEncoder().encode(encrypt);
            String strEncrypt = new String(encrypt);

            return strEncrypt;
        } catch (Exception e)
        {
            return e.getMessage();
        }
    }
}
