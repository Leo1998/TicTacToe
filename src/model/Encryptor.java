package model;

public class Encryptor {

    private static final char[] password;
    static {
        password = new String("STERBERATE").toCharArray();
    }

    public static String encrypt(String s) {
        char[] chars = s.toCharArray();
        char[] encrypted = new char[chars.length];

        for (int i = 0; i < chars.length; i++) {
            encrypted[i] = (char) (chars[i] + 10);
        }

        return new String(encrypted);
    }

    public static String decrypt(String s) {
        char[] encrypted = s.toCharArray();
        char[] chars = new char[encrypted.length];

        for (int i = 0; i < encrypted.length; i++) {
            chars[i] = (char) (encrypted[i] - 10);
        }

        return new String(chars);
    }

}
