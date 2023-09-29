package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println("\t Starting ...");

        try {
            // Generating objects of KeyGenerator &
            // SecretKey
            KeyGenerator keygenerator
                    = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            // Creating object of Cipher
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");

            File file = new File("aaa.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            System.out.println(file.getAbsolutePath());

            try{

                FileOutputStream os = new FileOutputStream("enc.txt");

                while(bufferedReader.read() != -1){

                    System.out.println("\t Encrypted byte[]");
                    System.out.println(Arrays.toString(bufferedReader.readLine().getBytes()));

                    // Encrypting text
                    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                    byte[] textEncrypted = desCipher.doFinal(bufferedReader.readLine().getBytes());

                    System.out.println("\t Converting encrypted byte array to string");
                    String s = new String(textEncrypted);
                    System.out.println(s);

                    os.write(textEncrypted, 0, textEncrypted.length);

                    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
                    byte[] textDecrypted
                            = desCipher.doFinal(textEncrypted);

                    System.out.println("\t Converting decrypted byte array to string");
                    s = new String(textDecrypted);
                    System.out.println(s);

                }

            }catch (Exception e){
                System.out.println("Exception: " + e);
            }

        }
        catch (Exception e) {
            System.out.println("Exception");
        }

        System.out.println("\t Done");

    }



}