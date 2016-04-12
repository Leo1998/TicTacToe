package model;

public class Encryptor {

    /**
     * encrypts a string (not working)
     *
     * @param s
     * @return
     */
    public static String encrypt(String s) {
        String result = "";
        int l = s.length();
        char c;

        for (int i = 0; i < l; i++) {
            c = s.charAt(i);
            c += 5;
            result += c;
        }

        return s;
    }

    /**
     * decrypts a string (not working)
     *
     * @param s
     * @return
     */
    public static String decrypt(String s) {
        String result = "";
        int l = s.length();
        char c;

        for (int i = 0; i < l; i++) {
            c = s.charAt(i);
            c -= 5;
            result += c;
        }

        return s;
    }

}
