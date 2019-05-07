package file;

import dictionary.Dictionary;
import java.lang.Throwable;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetFromFile {
    public static void fromAngToPol() {
        try {
            File file = new File("ang-pol.txt");
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String word = in.nextLine();
                String translationWord = in.nextLine();
                Dictionary.getEngDictionary().insert(word, translationWord);
                Dictionary.getPolishDictionary().insert(translationWord, word);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void fromPolToAng() {
        try {
            File filee = new File("pol-ang.txt");
            Scanner input = new Scanner(filee);
            while (input.hasNextLine()) {
                String word = input.nextLine();
                String translationWord = input.nextLine();
                Dictionary.getEngDictionary().insert(translationWord, word);
                Dictionary.getPolishDictionary().insert(word, translationWord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



