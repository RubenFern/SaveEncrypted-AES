public class Main
{
    public static void main(String[] args)
    {
        AES c = new AES();

        String password = "Contraseña";

        System.out.println("Sin cifrado: " + password);

        password = c.Encrypt(password);

        System.out.println("Con cifrado: " + password);

        password = c.Decrypt(password);

        System.out.println("Descifrado: " + password);
    }
}
