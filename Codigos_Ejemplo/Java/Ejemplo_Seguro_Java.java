import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class SecurePasswordExample {

    public static void main(String[] args) {
        // Ejemplo de generación y almacenamiento seguro de contraseñas

        // Paso 1: Generar una sal (salt) aleatoria
        byte[] salt = generateSalt();

        // Paso 2: Obtener la contraseña del usuario (simulación)
        String userPassword = "SecurePassword123";

        // Paso 3: Generar el hash de la contraseña
        byte[] hashedPassword = generateHash(userPassword, salt);

        // Paso 4: Almacenar el hash y la sal en la base de datos
        storeHashedPasswordAndSaltInDatabase(hashedPassword, salt);

        // Verificación del proceso de inicio de sesión
        verifyLogin(userPassword, hashedPassword, salt);
    }

    private static byte[] generateSalt() {
        // Utiliza SecureRandom para generar una sal aleatoria
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private static byte[] generateHash(String password, byte[] salt) {
        try {
            // Combina la contraseña con la sal
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] hashedPassword = messageDigest.digest(password.getBytes());

            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Manejo adecuado de la excepción en un entorno real
            return null;
        }
    }

    private static void storeHashedPasswordAndSaltInDatabase(byte[] hashedPassword, byte[] salt) {
        // En un entorno real, aquí se implementaría la lógica para almacenar
        // el hash y la sal en la base de datos asociada al usuario.
        System.out.println("Hash de contraseña almacenado en la base de datos.");
    }

    private static void verifyLogin(String enteredPassword, byte[] storedHash, byte[] salt) {
        // Verifica el inicio de sesión comparando el hash generado con el almacenado
        byte[] enteredPasswordHash = generateHash(enteredPassword, salt);

        if (Arrays.equals(enteredPasswordHash, storedHash)) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Inicio de sesión fallido. Las contraseñas no coinciden.");
        }
    }
}
