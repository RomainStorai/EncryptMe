package fr.romain.encryptme;

import fr.romain.encryptme.encryption.FileEncryption;

import javax.swing.*;

public class EncryptMe {

    public static void main(String[] args) {
        if (args.length < 2) {
            showHelp();
            return;
        }
        if (args[0].equalsIgnoreCase("encrypt")) {
            if (args.length >= 3) {
                char[] pass = args[2].toCharArray();
                FileEncryption.encrypt(args[1], pass);
            } if (args.length == 2) {
                FileEncryption.encrypt(args[1], requestPass("Encrypter"));
            } else {
                showHelp();
            }
        } else if (args[0].equalsIgnoreCase("decrypt")) {
            if (args.length >= 3) {
                char[] pass = args[2].toCharArray();
                FileEncryption.decrypt(args[1], pass);
            } if (args.length == 2) {
                FileEncryption.decrypt(args[1], requestPass("Décrypter"));
            } else {
                showHelp();
            }
        } else {
            showHelp();
        }
    }

    private static void showHelp() {
        System.out.println("Commande inconnue ! Usage:");
        System.out.println("... encrypt (fichier à encrypter)");
        System.out.println("... decrypt (fichier à decrypter)");
        System.out.println("... encrypt (fichier à encrypter) (pass)");
        System.out.println("... decrypt (fichier à decrypter) (pass)");
    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    private static char[] requestPass(String title) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Mot de passe : ");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        pass.addAncestorListener(new RequestFocusListener());
        String[] options = new String[]{"Annuler", "OK"};
        int option = JOptionPane.showOptionDialog(null, panel, title,
                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);
        if (option == 1) {
            return pass.getPassword();
        } else {
            System.exit(0);
        }
        return null;
    }
}
