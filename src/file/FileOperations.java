package file;

import dictionary.Dictionary;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.Scanner;

public class FileOperations {
    public static void addToDictionary() {
        try {
            File file = new File(Dictionary.dictionaryFileName);
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
//                String word;
//                String translationWord;
                String[] parts = in.nextLine().trim().split("-");
                //Wczytuj dalej tylko jeśli nie oznaczone do ignorowania
                if (parts[0].charAt(0) != '0') {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Nieprawidłowy format linii");
                    }
                    System.out.println(parts[0] + " - " + parts[1]);
                    Dictionary.getEngDictionary().insert(parts[0], parts[1]);
                    Dictionary.getPolishDictionary().insert(parts[1], parts[0]);
                }
            }
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Komunikat");
            alert.setHeaderText("Nieprawidłowy wyraz w pliku słownika!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Komunikat");
            alert.setHeaderText("Błąd wczytywania pliku słownika!");
            alert.showAndWait();
        }
    }


    public static void saveDicitionary(String ang, String pol) throws IOException {
        FileWriter fileWriter = new FileWriter(Dictionary.dictionaryFileName, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
//        printWriter.print("\n");
//        printWriter.print(ang);
//        printWriter.print("\n");
//        printWriter.print(pol);
        printWriter.println(ang + "-" + pol);
        printWriter.close();
    }

    public static void removeFromDictionary(String s, int position) {
        try {
            if (position != 0 && position != 1)
                throw new IllegalArgumentException("Pozycja musi wynosic 0(ang) lub 1(pol)");
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(Dictionary.dictionaryFileName));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                // replace the line here
                String[] parts = line.trim().split("-");
                //Próba usuwania linii - zapisz je tylko jak nie spełniają warunku
                if (!(parts[position].compareTo(s) == 0)) {
                    inputBuffer.append(line);
                    //Poprawione żeby działało na windowsach co mają \r\n a nie \n
                    inputBuffer.append(System.getProperty("line.separator"));
                }
            }
            file.close();
            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(Dictionary.dictionaryFileName);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Problem wczytywania pliku!");
            e.printStackTrace();
        }
    }

    public static void removeFromDictionary(String word, String translation) {
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(Dictionary.dictionaryFileName));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                // replace the line here
                String[] parts = line.trim().split("-");
                //Próba usuwania linii - zapisz je tylko jak nie spełniają warunku
                //Usuń tylko rekord gdzie pasuje słowo i tłumaczenie, resztę zapisz dalej
                if (!((parts[0].compareTo(word) == 0) && (parts[1].compareTo(translation) == 0))) {
                    inputBuffer.append(line);
                    //Poprawione żeby działało na windowsach co mają \r\n a nie \n
                    inputBuffer.append(System.getProperty("line.separator"));
                }
            }
            file.close();
            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(Dictionary.dictionaryFileName);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Problem wczytywania pliku!");
            e.printStackTrace();
        }
    }
}



