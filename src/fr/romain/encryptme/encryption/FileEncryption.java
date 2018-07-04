package fr.romain.encryptme.encryption;

import fr.romain.encryptme.EncryptMe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileEncryption {

    public static File encrypt(String file, char[] password) {
        File output = new File(file + ".eye");
        File input = new File(file);
        if (!exist(input)) {
            EncryptMe.showError("Fichier inconnu");
            return null;
        }
        try {
            AES.encrypt(128, password, new FileInputStream(input), new FileOutputStream(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static File decrypt(String file, char[] password) {
        if (!file.toLowerCase().endsWith(".eye")) {
            EncryptMe.showError("Ce n'est pas un fichier encrypté");
            return null;
        }
        File output = new File(replaceLast(file, ".eye", ""));
        File input = new File(file);
        if (!exist(input)) {
            EncryptMe.showError("Fichier inconnu");
            return null;
        }
        try {
            AES.decrypt(password, new FileInputStream(input), new FileOutputStream(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    private static boolean exist(File file) {
        return file.exists();
    }

    private static String replaceLast(String string, String query, String replace) {
        int start = string.lastIndexOf(query);

        StringBuilder builder = new StringBuilder();
        builder.append(string.substring(0, start));
        builder.append(replace);
        return builder.toString();
    }
}
