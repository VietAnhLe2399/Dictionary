import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
    public static void WriteDataToYourWord(Word w) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\data\\yourWord.txt", true));
        writer.append(w.getWord_target() + "\t" + w.getWord_explain() + "\n");

        writer.close();
    }

    public static void WriteDataToRecentWord(Word w) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\data\\RecentWord.txt", true));
        writer.append(w.getWord_target() + "\t" + w.getWord_explain() + "\n");

        writer.close();
    }
}
