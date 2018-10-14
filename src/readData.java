import javax.swing.text.html.ListView;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class readData {
    static public void ReadDataFromFile(HashMap d) {
        try {
            File inp = new File("src\\data\\dict.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inp), "UTF8"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] getWord = line.split("\t", 2);
                String word;
                String meaning = null;
                if (getWord.length == 1) {
                    word = getWord[0];
                } else {
                    word = getWord[0];
                    meaning = getWord[1];
                }
                Word w = new Word(word, meaning);
                d.put(word, meaning);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Something happened.");
        }
    }

    static public void ReadDataFromRecent(List arrayList) {
        try {
            File inp = new File("src\\data\\RecentWord.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inp), "UTF8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                arrayList.add(0, line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Something happened.");
        }
    }
}
