package file;

import dictionary.Dictionary;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOperations {
    public static void addToDictionary() {
        try {
            File file = new File("dictionary.txt");
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


        public static void saveDicitionary(String ang, String pol) throws IOException {

            FileWriter fileWriter = new FileWriter("dictionary.txt",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("\n");
            printWriter.print(ang);
            printWriter.print("\n");
            printWriter.print(pol);
            printWriter.close();

        }



}



