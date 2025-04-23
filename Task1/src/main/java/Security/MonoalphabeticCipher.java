package Security;

import java.util.*;

public class MonoalphabeticCipher {

    // TODO: Implement this method to generate a substitution map from A-Z using the provided key
    private static Map<Character, Character> generateEncryptionMap(String key) {
        Map<Character, Character> encryptionMap = new HashMap<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = key.toUpperCase();

        // Students should complete this loop
        for (int i = 0; i < alphabet.length(); i++) {
            // encryptionMap // Hint: Map plaintext letter to cipher letter
            if (Character.isLetter(key.charAt(i)))
            {
                encryptionMap.put(alphabet.charAt(i), key.charAt(i));
            }
        }
        return encryptionMap;
    }

    // TODO: Implement this method to reverse the encryption map (ciphertext -> plaintext)
    private static Map<Character, Character> generateDecryptionMap(String key) {
        Map<Character, Character> decryptionMap = new HashMap<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = key.toUpperCase();

        // Students should complete this loop
        for (int i = 0; i < alphabet.length(); i++) {
            // decryptionMap // Hint: Reverse mapping
            if (Character.isLetter(key.charAt(i)))
            {
                decryptionMap.put(key.charAt(i), alphabet.charAt(i));
            }
        }
        return decryptionMap;
    }

    public static String encrypt(String plaintext, String key) {
        Map<Character, Character> encryptionMap = generateEncryptionMap(key);
        plaintext = plaintext.toUpperCase();
        StringBuilder encryptedText = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            // TODO: Use the encryption map to convert each letter
            if (Character.isLetter(c))
            {
                encryptedText.append(encryptionMap.get(c));
            }
            else
            {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        Map<Character, Character> decryptionMap = generateDecryptionMap(key);
        ciphertext = ciphertext.toUpperCase();
        StringBuilder decryptedText = new StringBuilder();

        for (char c : ciphertext.toCharArray()) {
            // TODO: Use the decryption map to convert each letter
            if (Character.isLetter(c))
            {
                decryptedText.append(decryptionMap.get(c));
            }
            else
            {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    public static String findKey(String plaintext, String ciphertext) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet2 = new String(alphabet);
        char[] keyMap = new char[26];
        Arrays.fill(keyMap, ' ');

        plaintext = plaintext.toUpperCase();
        ciphertext = ciphertext.toUpperCase();

        Set<Character> usedCipherLetters = new HashSet<>();


        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char cipherChar = ciphertext.charAt(i);

            if (Character.isLetter(plainChar)) {
                int index = alphabet.indexOf(plainChar);
                // TODO: Ensure each letter is mapped only once

//                if (keyMap[index] != ' ') {
//                    if (keyMap[index] == cipherChar)
//                        continue;
//                }
//                if (new String(keyMap).indexOf(cipherChar) == -1) {
//                    keyMap[index] = cipherChar;
//
//                }

                if (keyMap[index] != ' ') {
                    // ...ensure it matches the current ciphertext letter.
                    if (keyMap[index] != cipherChar) {
                        // Inconsistency found: the same plaintext letter maps to different ciphertext letters.
                        throw new IllegalArgumentException("Inconsistent mapping for letter: " + plainChar);
                    }
                } else {
                    // Before assigning, check if the ciphertext letter is already used for a different plaintext letter.
                    if (usedCipherLetters.contains(cipherChar)) {
                        throw new IllegalArgumentException("Cipher letter " + cipherChar + " already mapped to another letter.");
                    } else {


                        keyMap[index] = cipherChar;
                        usedCipherLetters.add(cipherChar);
                    }
                }
            }
        }

//        for (int i = 0; i < 26; ++i)
//        {
//            if (keyMap[i] == ' ')
//            {
//                keyMap[i] = alphabet2.charAt(0);
//                alphabet2 = alphabet.substring()
//            }
//        }

                return new String(keyMap);
            }

    }