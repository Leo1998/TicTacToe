package model;

public class Encryptor {

    public static String encrypt(String s) {
        String result = "";
        int l = s.length();
        char c;

        for (int i = 0; i < l; i++) {
            c = s.charAt(i);
            c += 10;
            result += c;
        }

        return s;
    }

    public static String decrypt(String s) {
        String result = "";
        int l = s.length();
        char c;

        for (int i = 0; i < l; i++) {
            c = s.charAt(i);
            c -= 10;
            result += c;
        }

        return s;
    }

}
